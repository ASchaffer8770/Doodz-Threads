package com.doodzthreads.app.controller.admin;

import com.doodzthreads.app.domain.Design;
import com.doodzthreads.app.integrations.gelato.GelatoClient;
import com.doodzthreads.app.integrations.gelato.dto.GelatoProductSummary;
import com.doodzthreads.app.repository.DesignRepository;
import com.doodzthreads.app.util.SlugUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/designs/import/gelato")
public class AdminGelatoImportController {

    private final GelatoClient gelato;
    private final DesignRepository designRepository;

    public AdminGelatoImportController(GelatoClient gelato, DesignRepository designRepository) {
        this.gelato = gelato;
        this.designRepository = designRepository;
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int offset,
                       @RequestParam(defaultValue = "50") int limit,
                       @RequestParam(required = false) Integer alreadyImported,
                       Model model) {

        var resp = gelato.listProducts(offset, limit);

        List<GelatoProductSummary> products = (resp == null || resp.products() == null)
                ? List.of()
                : resp.products();

        // Build a quick lookup for "already imported"
        Set<String> importedIds = products.stream()
                .map(GelatoProductSummary::id)
                .filter(Objects::nonNull)
                .filter(designRepository::existsByGelatoProductId)
                .collect(Collectors.toSet());

        model.addAttribute("title", "Admin • Import from Gelato");
        model.addAttribute("products", products);
        model.addAttribute("importedIds", importedIds);
        model.addAttribute("offset", offset);
        model.addAttribute("limit", limit);
        model.addAttribute("alreadyImported", alreadyImported != null);

        return "admin/designs/gelato_import";
    }

    @PostMapping("/{productId}")
    public String importOne(@PathVariable String productId) {
        if (designRepository.existsByGelatoProductId(productId)) {
            return "redirect:/admin/designs/import/gelato?alreadyImported=1";
        }

        var detail = gelato.getProduct(productId);

        Design d = new Design();
        d.setTitle(detail.title());
        d.setDescription(detail.description());
        d.setStatus("DRAFT");
        d.setSlug(uniqueSlugFromTitle(detail.title()));
        d.setHeroImagePath(bestImage(detail));
        d.setGelatoProductId(productId);

        designRepository.save(d);
        return "redirect:/admin/designs";
    }

    @PostMapping("/backfill-images")
    public String backfillImages() {

        List<Design> designs = designRepository.findAll().stream()
                .filter(d -> d.getGelatoProductId() != null)
                .filter(d -> d.getHeroImagePath() == null || d.getHeroImagePath().isBlank())
                .toList();

        for (Design d : designs) {
            var detail = gelato.getProduct(d.getGelatoProductId());
            String image = bestImage(detail);

            if (image != null) {
                d.setHeroImagePath(image);
                designRepository.save(d);
            }
        }

        return "redirect:/admin/designs";
    }

    private String uniqueSlugFromTitle(String title) {
        String base = SlugUtils.slugify(title);
        if (base.isBlank()) base = "design";
        String candidate = base;
        int i = 2;
        while (designRepository.existsBySlug(candidate)) {
            candidate = base + "-" + i++;
        }
        return candidate;
    }

    private String bestImage(com.doodzthreads.app.integrations.gelato.dto.GelatoProductDetail detail) {
        if (detail == null) return null;

        if (detail.externalThumbnailUrl() != null && !detail.externalThumbnailUrl().isBlank()) {
            return detail.externalThumbnailUrl();
        }
        if (detail.previewUrl() != null && !detail.previewUrl().isBlank()) {
            return detail.previewUrl();
        }

        return null;
    }

}

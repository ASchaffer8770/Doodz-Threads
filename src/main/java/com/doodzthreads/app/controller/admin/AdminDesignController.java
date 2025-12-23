package com.doodzthreads.app.controller.admin;

import com.doodzthreads.app.domain.Design;
import com.doodzthreads.app.repository.DesignRepository;
import com.doodzthreads.app.util.SlugUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/designs")
public class AdminDesignController {

    private final DesignRepository designRepository;

    public AdminDesignController(DesignRepository designRepository) {
        this.designRepository = designRepository;
    }

    @GetMapping
    public String list(
            @RequestParam(required = false) String q,
            @RequestParam(required = false, defaultValue = "ALL") String status,
            Model model
    ) {
        // Minimal list behavior for Phase 1:
        // - If q is present, filter in-memory (simple, no new repo methods needed)
        // - If status != ALL, filter in-memory
        // (We can optimize with query methods later—this keeps Phase 1 quick.)

        List<Design> designs = designRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt"));

        if (q != null && !q.isBlank()) {
            String needle = q.toLowerCase();
            designs = designs.stream()
                    .filter(d -> d.getTitle() != null && d.getTitle().toLowerCase().contains(needle))
                    .toList();
        }

        if (status != null && !status.equalsIgnoreCase("ALL")) {
            String s = status.toUpperCase();
            designs = designs.stream()
                    .filter(d -> d.getStatus() != null && d.getStatus().equalsIgnoreCase(s))
                    .toList();
        }

        model.addAttribute("title", "Admin • Designs");
        model.addAttribute("designs", designs);
        model.addAttribute("q", q);
        model.addAttribute("status", status);
        return "admin/designs/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("title", "Admin • New Design");
        model.addAttribute("design", new Design());
        return "admin/designs/form";
    }

    @PostMapping
    public String create(@ModelAttribute Design design) {
        // slug
        if (design.getSlug() == null || design.getSlug().isBlank()) {
            design.setSlug(uniqueSlugFromTitle(design.getTitle(), null));
        } else {
            design.setSlug(uniqueSlug(design.getSlug(), null));
        }

        // status
        if (design.getStatus() == null || design.getStatus().isBlank()) {
            design.setStatus("DRAFT");
        } else {
            design.setStatus(design.getStatus().toUpperCase());
        }

        designRepository.save(design);
        return "redirect:/admin/designs";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Design design = designRepository.findById(id).orElseThrow();
        model.addAttribute("title", "Admin • Edit Design");
        model.addAttribute("design", design);
        return "admin/designs/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Design incoming) {
        Design design = designRepository.findById(id).orElseThrow();

        design.setTitle(incoming.getTitle());
        design.setDescription(incoming.getDescription());
        design.setHeroImagePath(incoming.getHeroImagePath());

        // status normalization
        if (incoming.getStatus() == null || incoming.getStatus().isBlank()) {
            design.setStatus("DRAFT");
        } else {
            design.setStatus(incoming.getStatus().toUpperCase());
        }

        // slug rule:
        // - If admin leaves slug blank: keep existing slug stable
        // - If admin provides slug and it's different: enforce uniqueness excluding this record
        if (incoming.getSlug() != null && !incoming.getSlug().isBlank()) {
            String requested = SlugUtils.slugify(incoming.getSlug());
            if (!requested.equalsIgnoreCase(design.getSlug())) {
                design.setSlug(uniqueSlug(requested, id));
            }
        }

        designRepository.save(design);
        return "redirect:/admin/designs";
    }

    // Soft actions (Phase 1)
    @PostMapping("/{id}/archive")
    public String archive(@PathVariable Long id) {
        Design design = designRepository.findById(id).orElseThrow();
        design.setStatus("ARCHIVED");
        designRepository.save(design);
        return "redirect:/admin/designs";
    }

    @PostMapping("/{id}/unarchive")
    public String unarchive(@PathVariable Long id) {
        Design design = designRepository.findById(id).orElseThrow();
        design.setStatus("DRAFT");
        designRepository.save(design);
        return "redirect:/admin/designs";
    }

    @PostMapping("/{id}/publish")
    public String publish(@PathVariable Long id) {
        Design design = designRepository.findById(id).orElseThrow();
        design.setStatus("PUBLISHED");
        designRepository.save(design);
        return "redirect:/admin/designs";
    }

    private String uniqueSlugFromTitle(String title, Long excludeId) {
        return uniqueSlug(SlugUtils.slugify(title), excludeId);
    }

    private String uniqueSlug(String base, Long excludeId) {
        String slug = SlugUtils.slugify(base);
        if (slug.isBlank()) slug = "design";

        String candidate = slug;
        int i = 2;

        while (excludeId == null
                ? designRepository.existsBySlug(candidate)
                : designRepository.existsBySlugAndIdNot(candidate, excludeId)) {
            candidate = slug + "-" + i;
            i++;
        }

        return candidate;
    }
}

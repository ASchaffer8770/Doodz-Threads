package com.doodzthreads.app.controller.admin;

import com.doodzthreads.app.domain.Design;
import com.doodzthreads.app.repository.DesignRepository;
import com.doodzthreads.app.util.SlugUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/designs")
public class AdminDesignController {

    private final DesignRepository designRepository;

    public AdminDesignController(DesignRepository designRepository) {
        this.designRepository = designRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("title", "Admin • Designs");
        model.addAttribute("designs", designRepository.findAll());
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
        if (design.getSlug() == null || design.getSlug().isBlank()) {
            design.setSlug(uniqueSlugFromTitle(design.getTitle()));
        } else {
            design.setSlug(uniqueSlug(design.getSlug()));
        }
        if (design.getStatus() == null || design.getStatus().isBlank()) {
            design.setStatus("DRAFT");
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
        design.setStatus(incoming.getStatus());

        // Only regenerate slug if admin provided one (keeps links stable)
        if (incoming.getSlug() != null && !incoming.getSlug().isBlank()
                && !incoming.getSlug().equals(design.getSlug())) {
            design.setSlug(uniqueSlug(incoming.getSlug()));
        }

        designRepository.save(design);
        return "redirect:/admin/designs";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        designRepository.deleteById(id);
        return "redirect:/admin/designs";
    }

    private String uniqueSlugFromTitle(String title) {
        return uniqueSlug(SlugUtils.slugify(title));
    }

    private String uniqueSlug(String base) {
        String slug = SlugUtils.slugify(base);
        if (slug.isBlank()) slug = "design";
        String candidate = slug;
        int i = 2;
        while (designRepository.existsBySlug(candidate)) {
            candidate = slug + "-" + i;
            i++;
        }
        return candidate;
    }
}

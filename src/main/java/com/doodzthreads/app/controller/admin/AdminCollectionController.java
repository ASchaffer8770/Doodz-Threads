package com.doodzthreads.app.controller.admin;

import com.doodzthreads.app.domain.Collection;
import com.doodzthreads.app.repository.CollectionRepository;
import com.doodzthreads.app.repository.DesignRepository;
import com.doodzthreads.app.util.SlugUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/admin/collections")
public class AdminCollectionController {

    private final CollectionRepository collectionRepository;
    private final DesignRepository designRepository;
    private final JdbcTemplate jdbc;

    public AdminCollectionController(
            CollectionRepository collectionRepository,
            DesignRepository designRepository,
            JdbcTemplate jdbc
    ) {
        this.collectionRepository = collectionRepository;
        this.designRepository = designRepository;
        this.jdbc = jdbc;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("title", "Admin • Collections");
        model.addAttribute("collections", collectionRepository.findAll());
        return "admin/collections/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Collection c = collectionRepository.findById(id).orElseThrow();

        // Designs currently in this collection (ordered by position)
        List<Map<String, Object>> items = jdbc.queryForList("""
            SELECT d.id, d.title, d.slug, d.status, cd.position
            FROM collection_designs cd
            JOIN designs d ON d.id = cd.design_id
            WHERE cd.collection_id = ?
            ORDER BY cd.position ASC, d.id ASC
        """, id);

        model.addAttribute("title", "Admin • Collection");
        model.addAttribute("collection", c);
        model.addAttribute("items", items);
        model.addAttribute("allDesigns", designRepository.findAll());
        return "admin/collections/detail";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Collection incoming) {
        Collection c = collectionRepository.findById(id).orElseThrow();

        c.setName(incoming.getName());
        c.setDescription(incoming.getDescription());
        c.setStatus(incoming.getStatus());

        if (incoming.getSlug() != null && !incoming.getSlug().isBlank()
                && !incoming.getSlug().equals(c.getSlug())) {
            c.setSlug(uniqueSlug(incoming.getSlug()));
        }

        collectionRepository.save(c);
        return "redirect:/admin/collections/" + id;
    }

    @PostMapping("/{id}/add-design")
    public String addDesign(@PathVariable Long id, @RequestParam("designId") Long designId) {
        // if already exists, do nothing
        Integer exists = jdbc.queryForObject("""
            SELECT COUNT(*) FROM collection_designs
            WHERE collection_id = ? AND design_id = ?
        """, Integer.class, id, designId);

        if (exists != null && exists > 0) {
            return "redirect:/admin/collections/" + id;
        }

        Integer maxPos = jdbc.queryForObject("""
            SELECT COALESCE(MAX(position), -1) FROM collection_designs
            WHERE collection_id = ?
        """, Integer.class, id);

        int nextPos = (maxPos == null ? 0 : maxPos + 1);

        jdbc.update("""
            INSERT INTO collection_designs (collection_id, design_id, position)
            VALUES (?, ?, ?)
        """, id, designId, nextPos);

        return "redirect:/admin/collections/" + id;
    }

    @PostMapping("/{id}/remove-design")
    public String removeDesign(@PathVariable Long id, @RequestParam("designId") Long designId) {
        jdbc.update("""
            DELETE FROM collection_designs
            WHERE collection_id = ? AND design_id = ?
        """, id, designId);

        return "redirect:/admin/collections/" + id;
    }

    private String uniqueSlug(String base) {
        String slug = SlugUtils.slugify(base);
        if (slug.isBlank()) slug = "collection";
        String candidate = slug;
        int i = 2;
        while (collectionRepository.existsBySlug(candidate)) {
            candidate = slug + "-" + i;
            i++;
        }
        return candidate;
    }
}

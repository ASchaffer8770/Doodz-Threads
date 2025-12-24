package com.doodzthreads.app.controller;

import com.doodzthreads.app.domain.Collection;
import com.doodzthreads.app.repository.CollectionRepository;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/collections")
public class CollectionsController {

    private final CollectionRepository collectionRepository;

    public CollectionsController(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @GetMapping
    public String index(
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "newest") String sort,
            @RequestParam(defaultValue = "0") int page,
            Model model
    ) {
        Pageable pageable = PageRequest.of(page, 12, toSort(sort));
        Page<Collection> collections = collectionRepository.searchPublished(q, pageable);

        model.addAttribute("q", q);
        model.addAttribute("sort", sort);
        model.addAttribute("collections", collections);

        return "collections/index";
    }

    @GetMapping("/{slug}")
    public String showCollection(@PathVariable String slug, Model model) {

        Collection collection = collectionRepository
                .findPublishedBySlugWithOrderedDesigns(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        model.addAttribute("title", collection.getName() + " • Doodz Threads");
        model.addAttribute("collection", collection);

        return "collections/show";
    }

    private Sort toSort(String sort) {
        return switch (sort) {
            case "az" -> Sort.by(Sort.Direction.ASC, "name");
            case "za" -> Sort.by(Sort.Direction.DESC, "name");
            case "oldest" -> Sort.by(Sort.Direction.ASC, "createdAt");
            default -> Sort.by(Sort.Direction.DESC, "createdAt"); // newest
        };
    }
}

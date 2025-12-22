package com.doodzthreads.app.repository;

import com.doodzthreads.app.domain.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Optional<Collection> findBySlug(String slug);
    boolean existsBySlug(String slug);
}

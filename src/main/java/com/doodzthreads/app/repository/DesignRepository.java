package com.doodzthreads.app.repository;

import com.doodzthreads.app.domain.Design;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DesignRepository extends JpaRepository<Design, Long> {
    Optional<Design> findBySlug(String slug);
    boolean existsBySlug(String slug);
}

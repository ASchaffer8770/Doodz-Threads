package com.doodzthreads.app.repository;

import com.doodzthreads.app.domain.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface DesignRepository extends JpaRepository<Design, Long> {
    Optional<Design> findBySlug(String slug);
    boolean existsBySlug(String slug);
    boolean existsBySlugAndIdNot(String slug, Long id);
    boolean existsByGelatoProductId(String gelatoProductId);

    // --- cleanup join tables before deleting a Design ---

    @Modifying
    @Transactional
    @Query(value = "delete from collection_designs where design_id = ?1", nativeQuery = true)
    void deleteFromCollectionDesigns(Long designId);

    @Modifying
    @Transactional
    @Query(value = "delete from drop_designs where design_id = ?1", nativeQuery = true)
    void deleteFromDropDesigns(Long designId);
}

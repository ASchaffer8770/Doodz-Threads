package com.doodzthreads.app.repository;

import com.doodzthreads.app.domain.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    Optional<Collection> findBySlug(String slug);
    boolean existsBySlug(String slug);

    @Query("""
        select c from Collection c
        where c.status = 'PUBLISHED'
          and (
            :q is null or :q = '' or
            lower(c.name) like lower(concat('%', :q, '%')) or
            lower(coalesce(c.description, '')) like lower(concat('%', :q, '%'))
          )
        """)
    Page<Collection> searchPublished(@Param("q") String q, Pageable pageable);

    @Query("""
       select distinct c
       from Collection c
       left join fetch c.collectionDesigns cd
       left join fetch cd.design d
       where c.slug = :slug
         and c.status = 'PUBLISHED'
       order by cd.position asc
       """)
    Optional<Collection> findPublishedBySlugWithOrderedDesigns(@Param("slug") String slug);

}

package com.doodzthreads.app.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "collection_designs")
public class CollectionDesign {

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("collectionId")
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("designId")
    @JoinColumn(name = "design_id", nullable = false)
    private Design design;

    @Column(nullable = false)
    private int position = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void onCreate() {
        if (createdAt == null) createdAt = Instant.now();
    }

    // --- getters ---
    public Collection getCollection() { return collection; }
    public Design getDesign() { return design; }
    public int getPosition() { return position; }
    public Instant getCreatedAt() { return createdAt; }

    // --- setters (needed by JPA + when creating links) ---
    public void setCollection(Collection collection) { this.collection = collection; }
    public void setDesign(Design design) { this.design = design; }
    public void setPosition(int position) { this.position = position; }

    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "collection_id")
        private Long collectionId;

        @Column(name = "design_id")
        private Long designId;

        public Id() {}

        public Id(Long collectionId, Long designId) {
            this.collectionId = collectionId;
            this.designId = designId;
        }

        public Long getCollectionId() { return collectionId; }
        public Long getDesignId() { return designId; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id that = (Id) o;
            return collectionId != null && collectionId.equals(that.collectionId)
                    && designId != null && designId.equals(that.designId);
        }

        @Override
        public int hashCode() {
            int result = (collectionId != null ? collectionId.hashCode() : 0);
            result = 31 * result + (designId != null ? designId.hashCode() : 0);
            return result;
        }
    }
}

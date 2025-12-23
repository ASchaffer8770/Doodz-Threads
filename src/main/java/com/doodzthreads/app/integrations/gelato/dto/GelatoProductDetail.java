package com.doodzthreads.app.integrations.gelato.dto;

import java.util.List;

public record GelatoProductDetail(
        String id,
        String title,
        String description,
        List<GelatoImage> images
) {
    public String bestImageUrl() {
        if (images == null || images.isEmpty()) return null;
        // pick first for now; we can improve later
        return images.get(0).url();
    }
}

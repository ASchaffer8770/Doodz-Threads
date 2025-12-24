package com.doodzthreads.app.integrations.gelato.dto;

public record GelatoProductSummary(
        String id,
        String title,
        String description,
        String previewUrl,
        String externalThumbnailUrl,
        String createdAt
) {}

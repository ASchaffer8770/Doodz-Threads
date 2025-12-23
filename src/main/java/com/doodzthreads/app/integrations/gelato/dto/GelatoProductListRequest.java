package com.doodzthreads.app.integrations.gelato.dto;

public record GelatoProductListRequest(
        String order,
        String orderBy,
        int offset,
        int limit
) {}

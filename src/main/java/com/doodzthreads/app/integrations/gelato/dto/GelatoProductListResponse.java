package com.doodzthreads.app.integrations.gelato.dto;

import java.util.List;

public record GelatoProductListResponse(
        List<GelatoProductSummary> products
) {}

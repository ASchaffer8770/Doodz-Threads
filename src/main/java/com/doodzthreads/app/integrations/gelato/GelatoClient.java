package com.doodzthreads.app.integrations.gelato;

import com.doodzthreads.app.integrations.gelato.dto.GelatoProductDetail;
import com.doodzthreads.app.integrations.gelato.dto.GelatoProductListRequest;
import com.doodzthreads.app.integrations.gelato.dto.GelatoProductListResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GelatoClient {

    private final RestClient rest;
    private final String storeId;

    public GelatoClient(
            @Value("${gelato.baseUrl}") String baseUrl,
            @Value("${gelato.apiKey}") String apiKey,
            @Value("${gelato.storeId}") String storeId
    ) {
        this.storeId = storeId;
        this.rest = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("X-API-KEY", apiKey)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public GelatoProductListResponse listProducts(int offset, int limit) {
        // Gelato docs show GET with a JSON body for paging/sort. :contentReference[oaicite:3]{index=3}
        // RestClient can send a body with GET using method(...) which is the most compatible way to match docs.
        GelatoProductListRequest req = new GelatoProductListRequest("desc", "createdAt", offset, limit);

        return rest.method(org.springframework.http.HttpMethod.GET)
                .uri("/v1/stores/{storeId}/products", storeId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(req)
                .retrieve()
                .body(GelatoProductListResponse.class);
    }

    public GelatoProductDetail getProduct(String productId) {
        // Docs: GET /v1/stores/{storeId}/products/{productId} :contentReference[oaicite:4]{index=4}
        return rest.get()
                .uri("/v1/stores/{storeId}/products/{productId}", storeId, productId)
                .retrieve()
                .body(GelatoProductDetail.class);
    }
}

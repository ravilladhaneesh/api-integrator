package com.example.integration.service;

import com.example.integration.model.ApiConfig;
import com.example.integration.model.ApiRequestConfig;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenericApiExecutor {

    private final RestTemplate restTemplate;

    public GenericApiExecutor(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public ResponseEntity<String> execute(ApiConfig apiConfig, ApiRequestConfig requestConfig) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(apiConfig.getHeaders());

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = apiConfig.getBaseUrl() + requestConfig.getEndpoint();

        return restTemplate.exchange(
                url,
                HttpMethod.valueOf(requestConfig.getHttpMethod()),
                entity,
                String.class
        );
    }
}
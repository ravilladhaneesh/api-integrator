package com.example.integration.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.integration.auth.AuthHandler;
import com.example.integration.auth.AuthHandlerFactory;
import com.example.integration.model.ApiConfig;
import com.example.integration.model.ApiRequestConfig;

@Service
public class GenericApiExecutor {

    private final RestTemplate restTemplate;
    private final AuthHandlerFactory authHandlerFactory;
    

    public GenericApiExecutor(RestTemplateBuilder builder, AuthHandlerFactory authHandlerFactory) {
        this.restTemplate = builder.build();
        this.authHandlerFactory = authHandlerFactory;
    }

    public ResponseEntity<String> execute(ApiConfig apiConfig, ApiRequestConfig requestConfig) {
    	
    	HttpHeaders headers = new HttpHeaders();
    	
    	AuthHandler authHandler =
                authHandlerFactory.getHandler(apiConfig.getAuthType());
    	authHandler.apply(headers, apiConfig);
    	
        
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
package com.example.integration.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.example.integration.model.ApiConfig;

@Component
public class BearerTokenAuthHandler implements AuthHandler {

    @Override
    public void apply(HttpHeaders headers, ApiConfig config) {
        headers.setAll(config.getHeaders());
    }
}


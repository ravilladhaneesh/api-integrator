package com.example.integration.auth;

import com.example.integration.model.ApiConfig;
import org.springframework.http.HttpHeaders;

public interface AuthHandler {
    void apply(HttpHeaders headers, ApiConfig config);
}

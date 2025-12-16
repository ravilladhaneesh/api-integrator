package com.example.integration.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.example.integration.model.ApiConfig;

@Component
public class OAuthAuthHandler implements AuthHandler {

    // To-do: inject TokenService

    @Override
    public void apply(HttpHeaders headers, ApiConfig config) {
    	
    	
    	// To-do
        // Placeholder – runtime token generation
    	
    	
        String accessToken = "OAUTH_ACCESS_TOKEN";
        headers.setBearerAuth(accessToken);
    }
}


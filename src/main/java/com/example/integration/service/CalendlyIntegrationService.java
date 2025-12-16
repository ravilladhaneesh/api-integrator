package com.example.integration.service;

import com.example.integration.model.*;
import com.example.integration.repository.ExternalUserRepository;
import com.example.integration.util.JsonMappingUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CalendlyIntegrationService {

    private final GenericApiExecutor executor;
    private final ExternalUserRepository repository;

    public CalendlyIntegrationService(GenericApiExecutor executor, ExternalUserRepository repository) {
        this.executor = executor;
        this.repository = repository;
    }

    public void fetch(ApiConfig config, ApiRequestConfig request) throws Exception {
    	
        ResponseEntity<String> response = executor.execute(config, request);
        parseResponse(request, response);
    }
    
    private void parseResponse(ApiRequestConfig request, ResponseEntity<String> response) throws Exception {
        
    	// parse Based on request Endpoint
        if(request.getEndpoint().equals("/users/me")) {
        	saveUser(request, response);
        }
        //To-do for other endpoints
        
    }
    
    private void saveUser(ApiRequestConfig request, ResponseEntity<String> response) throws Exception {
    	JsonNode root = new ObjectMapper().readTree(response.getBody());
        Map<String, String> responseMapping = request.getResponseMapping();
    	ExternalUser user = new ExternalUser();
    	
        user.setExternalId(JsonMappingUtil.getValue(root, responseMapping.get("id")));
        user.setEmail(JsonMappingUtil.getValue(root, responseMapping.get("email")));
        user.setName(JsonMappingUtil.getValue(root, responseMapping.get("name")));
        user.setSourceApp("CALENDLY");
        user.setRawPayload(response.getBody());
        
        repository.save(user);
    }
}
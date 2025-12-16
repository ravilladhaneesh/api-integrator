package com.example.integration.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.integration.model.ApiRequestConfig;
import com.example.integration.repository.ApiConfigRepository;
import com.example.integration.repository.ApiRequestConfigRepository;

@Service
public class IntegrationService {

    private final ApiConfigRepository apiConfigRepository;
    private final ApiRequestConfigRepository requestConfigRepository;
    private final CalendlyIntegrationService calendlyService;

    public IntegrationService(ApiConfigRepository apiConfigRepository,
                              ApiRequestConfigRepository requestConfigRepository,
                              CalendlyIntegrationService calendlyService) {
        this.apiConfigRepository = apiConfigRepository;
        this.requestConfigRepository = requestConfigRepository;
        this.calendlyService = calendlyService;
    }

    public void sync(String app) throws Exception {
        var apiConfig = apiConfigRepository.findByAppName(app).orElseThrow();
        var requestConfigs = requestConfigRepository.findAllByAppName(app);
        
        for(ApiRequestConfig requestConfig: requestConfigs) {
        	if ("CALENDLY".equalsIgnoreCase(app)) {
                calendlyService.fetch(apiConfig, requestConfig);
            }
        }
                
    }
}
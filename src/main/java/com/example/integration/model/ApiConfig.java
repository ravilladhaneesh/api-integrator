package com.example.integration.model;

import jakarta.persistence.*;
import java.util.Map;

@Entity
public class ApiConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appName;
    private String baseUrl;
    private String authType;

    @Convert(converter = JsonMapConverter.class)
    @Column(columnDefinition = "CLOB")
    private Map<String, String> headers;

    public Long getId() { return id; }
    public String getAppName() { return appName; }
    public String getBaseUrl() { return baseUrl; }
    public String getAuthType() { return authType; }
    public Map<String, String> getHeaders() { return headers; }

    public void setId(Long id) { this.id = id; }
    public void setAppName(String appName) { this.appName = appName; }
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }
    public void setAuthType(String authType) { this.authType = authType; }
    public void setHeaders(Map<String, String> headers) { this.headers = headers; }
}
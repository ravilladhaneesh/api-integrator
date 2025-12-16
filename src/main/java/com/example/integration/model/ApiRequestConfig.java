package com.example.integration.model;

import jakarta.persistence.*;
import java.util.Map;

@Entity
public class ApiRequestConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appName;
    private String endpoint;
    private String httpMethod;

    @Convert(converter = JsonMapConverter.class)
    @Column(columnDefinition = "CLOB")
    private Map<String, String> responseMapping;

    public Long getId() { return id; }
    public String getAppName() { return appName; }
    public String getEndpoint() { return endpoint; }
    public String getHttpMethod() { return httpMethod; }
    public Map<String, String> getResponseMapping() { return responseMapping; }

    public void setId(Long id) { this.id = id; }
    public void setAppName(String appName) { this.appName = appName; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }
    public void setHttpMethod(String httpMethod) { this.httpMethod = httpMethod; }
    public void setResponseMapping(Map<String, String> responseMapping) { this.responseMapping = responseMapping; }
}
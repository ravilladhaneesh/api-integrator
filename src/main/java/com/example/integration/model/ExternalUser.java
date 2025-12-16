package com.example.integration.model;

import jakarta.persistence.*;

@Entity
public class ExternalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalId;
    private String email;
    private String name;
    private String sourceApp;

    @Lob
    private String rawPayload;

    public Long getId() { return id; }
    public String getExternalId() { return externalId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getSourceApp() { return sourceApp; }
    public String getRawPayload() { return rawPayload; }

    public void setId(Long id) { this.id = id; }
    public void setExternalId(String externalId) { this.externalId = externalId; }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public void setSourceApp(String sourceApp) { this.sourceApp = sourceApp; }
    public void setRawPayload(String rawPayload) { this.rawPayload = rawPayload; }
}
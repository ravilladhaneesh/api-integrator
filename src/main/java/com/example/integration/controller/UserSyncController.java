package com.example.integration.controller;

import com.example.integration.service.IntegrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sync")
public class UserSyncController {

    private final IntegrationService integrationService;

    public UserSyncController(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @PostMapping("/{app}")
    public ResponseEntity<String> sync(@PathVariable String app) throws Exception {
        integrationService.sync(app.toUpperCase());
        return ResponseEntity.ok("Sync completed for " + app);
    }
}
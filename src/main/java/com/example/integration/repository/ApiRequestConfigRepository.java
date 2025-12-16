package com.example.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.integration.model.ApiRequestConfig;

public interface ApiRequestConfigRepository extends JpaRepository<ApiRequestConfig, Long> {
    List<ApiRequestConfig> findAllByAppName(String appName);
}
package com.example.integration.repository;

import com.example.integration.model.ExternalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalUserRepository extends JpaRepository<ExternalUser, Long> {
}
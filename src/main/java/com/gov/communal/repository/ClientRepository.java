package com.gov.communal.repository;

import com.gov.communal.model.auth.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    boolean existsByEmail(String email);
}

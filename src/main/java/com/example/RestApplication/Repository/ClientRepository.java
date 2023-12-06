package com.example.RestApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.RestApplication.Entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // Custom queries if needed
}

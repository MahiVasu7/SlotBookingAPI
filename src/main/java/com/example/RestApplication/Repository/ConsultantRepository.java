package com.example.RestApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.RestApplication.Entities.Consultant;

public interface ConsultantRepository extends JpaRepository<Consultant, Long> {
    // Custom queries if needed
    List<Consultant> findByAreaOfExpertiseIgnoreCase(String areaOfExpertise);
}

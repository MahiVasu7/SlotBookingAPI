package com.example.RestApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.RestApplication.Entities.Client;
import com.example.RestApplication.Entities.Consultant;
import com.example.RestApplication.Service.ConsultantService;

import java.util.List;

@RestController
@RequestMapping("/consultants")
public class ConsultantController {

    @Autowired
    private ConsultantService consultantService;
    
    
    @GetMapping()
    public List<Consultant> getAllConsultants() {
        return consultantService.getAllClients();
    }

    @GetMapping("/{id}")
    public Consultant getConsultantById(@PathVariable Long id) {
        return consultantService.getConsultantById(id).orElse(null);
    }

    @GetMapping("/area/{areaOfExpertise}")
    public ResponseEntity<List<Consultant>> getConsultantsByArea(@PathVariable String areaOfExpertise) {
        List<Consultant> consultants = consultantService.findConsultantsByArea(areaOfExpertise);
        return ResponseEntity.ok(consultants);
    }
    @PostMapping("/create")
    public Consultant addConsultant(@RequestBody Consultant consultant) {
        return consultantService.saveConsultant(consultant);
    }
}

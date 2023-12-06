package com.example.RestApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RestApplication.Entities.Client;
import com.example.RestApplication.Entities.Consultant;
import com.example.RestApplication.Repository.ConsultantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultantService {

    @Autowired
    private ConsultantRepository consultantRepository;

    public Optional<Consultant> getConsultantById(Long id) {
        return consultantRepository.findById(id);
    }

    public List<Consultant> findConsultantsByArea(String areaOfExpertise) {
        return consultantRepository.findByAreaOfExpertiseIgnoreCase(areaOfExpertise);
    }
    public Consultant saveConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

	public List<Consultant> getAllClients() {
		// TODO Auto-generated method stub
		return consultantRepository.findAll();	
		}
}

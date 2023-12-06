package com.example.RestApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.RestApplication.Entities.Slot;
import com.example.RestApplication.Repository.SlotRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;
    public Slot createSlot(Slot slot) {
        // Check if the slot already exists
        Optional<Slot> existingSlot = slotRepository.findByDateAndStartTimeAndEndTimeAndConsultantId(
                slot.getDate(), slot.getStartTime(), slot.getEndTime(), slot.getConsultantId());

        if (existingSlot.isPresent()) {
            // Slot with the same key attributes already exists, handle accordingly
            // You may throw an exception, return an error response, or take appropriate action
            // For simplicity, let's assume we don't allow duplicates and return null
            return null;
        }

        // Save the slot if it doesn't exist
        return slotRepository.save(slot);
    }
    @Transactional
    public ResponseEntity<String> bookSlot(Long consultantId, String date, Long clientId) {
        // Check if the slot exists and is available
        Slot slot = slotRepository.findAvailableSlot(consultantId, date);

        if (slot != null) {
            // Book the slot
            int updatedRows = slotRepository.bookSlot(slot.getId(), clientId);
            return updatedRows > 0
                ? new ResponseEntity<>("Slot booked successfully", HttpStatus.OK)
                : new ResponseEntity<>("Unable to book the slot", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Slot not available or does not exist", HttpStatus.BAD_REQUEST);
    }



    public Optional<Slot> getSlotById(Long id) {
        return slotRepository.findById(id);
    }

    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    public Slot saveSlot(Slot slot) {
        return slotRepository.save(slot);
    }
    public List<Slot> getSlotsByConsultantId(Long consultantId) {
        return slotRepository.findByConsultantId(consultantId);
    }}

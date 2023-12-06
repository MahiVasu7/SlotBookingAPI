package com.example.RestApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.RestApplication.Entities.BookingRequest;
import com.example.RestApplication.Entities.Slot;
import com.example.RestApplication.Service.SlotService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/slots")
@Validated
public class SlotController {

    @Autowired
    private SlotService slotService;
    @PostMapping("/create")
    public ResponseEntity<String> createSlot(@RequestBody Slot slot) {
        Slot createdSlot = slotService.createSlot(slot);

        if (createdSlot != null) {
            return new ResponseEntity<>("Slot created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Slot already exists or invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookSlot(@RequestBody BookingRequest request) {
        Long consultantId = request.getConsultantId();
        String date = request.getDate();
        Long clientId = request.getClientId();

        return slotService.bookSlot(consultantId, date, clientId);
    }

    @GetMapping()
    public ResponseEntity<List<Slot>> getAllSlots() {
        List<Slot> slots = slotService.getAllSlots();
        return new ResponseEntity<>(slots, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Slot> getSlotById(@PathVariable Long id) {
        Optional<Slot> slot = slotService.getSlotById(id);

        return slot.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/consultant/{consultantId}")
    public ResponseEntity<List<Slot>> getSlotsByConsultantId(@PathVariable Long consultantId) {
        List<Slot> consultantSlots = slotService.getSlotsByConsultantId(consultantId);

        if (!consultantSlots.isEmpty()) {
            return new ResponseEntity<>(consultantSlots, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

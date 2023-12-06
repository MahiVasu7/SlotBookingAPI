package com.example.RestApplication.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.RestApplication.Entities.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    // Custom queries if needed
    Optional<Slot> findByDateAndStartTimeAndEndTimeAndConsultantId(String date, String startTime, String endTime, Long consultantId);

    @Modifying
    @Query("UPDATE Slot s SET s.bookingStatus = 'BOOKED', s.clientId = :clientId WHERE s.id = :slotId AND s.bookingStatus = 'AVAILABLE'")
    int bookSlot(@Param("slotId") Long slotId, @Param("clientId") Long clientId);

    @Query("SELECT s FROM Slot s WHERE s.consultantId = :consultantId AND s.date = :date AND s.bookingStatus = 'AVAILABLE'")
    Slot findAvailableSlot(@Param("consultantId") Long consultantId, @Param("date") String date);

    
	Slot findByConsultantIdAndDateAndBookingStatus(Long consultantId, String date, String string);

	List<Slot> findByConsultantId(Long consultantId);
}

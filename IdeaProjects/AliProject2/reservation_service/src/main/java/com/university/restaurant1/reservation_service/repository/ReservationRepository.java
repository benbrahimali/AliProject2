package com.university.restaurant1.reservation_service.repository;


import com.university.restaurant1.reservation_service.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.tableNumber = :tableNumber AND " +
            "(r.arrivalTime < :departureTime AND r.departureTime > :arrivalTime)")
    List<Reservation> findByTableNumberAndOverlap(
            @Param("tableNumber") Integer tableNumber,
            @Param("arrivalTime") LocalDateTime arrivalTime,
            @Param("departureTime") LocalDateTime departureTime
    );
}
package com.university.restaurant1.reservation_service.controller;
import com.university.restaurant1.reservation_service.dto.ReservationRequest;
import com.university.restaurant1.reservation_service.dto.ReservationResponse;
import com.university.restaurant1.reservation_service.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest reservationRequest) {
        ReservationResponse response = reservationService.createReservation(reservationRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        List<ReservationResponse> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable Long id) {
        ReservationResponse reservation = reservationService.getReservationById(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponse> updateReservation(@PathVariable Long id, @RequestBody ReservationRequest reservationRequest) {
        ReservationResponse updatedReservation = reservationService.updateReservation(id, reservationRequest);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


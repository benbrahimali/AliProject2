package com.university.restaurant1.reservation_service.service;


import com.university.restaurant1.reservation_service.dto.ReservationRequest;
import com.university.restaurant1.reservation_service.dto.ReservationResponse;
import com.university.restaurant1.reservation_service.model.Reservation;
import com.university.restaurant1.reservation_service.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // Ensure all operations are part of a transaction
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    private static final int MAX_TABLES = 20;

    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        validateTableNumber(reservationRequest.getTableNumber());
        checkTableAvailability(
                reservationRequest.getTableNumber(),
                reservationRequest.getArrivalTime(),
                reservationRequest.getDepartureTime()
        );

        Reservation reservation = new Reservation();
        reservation.setClientName(reservationRequest.getClientName());
        reservation.setArrivalTime(reservationRequest.getArrivalTime());
        reservation.setDepartureTime(reservationRequest.getDepartureTime());
        reservation.setNumberOfPeople(reservationRequest.getNumberOfPeople());
        reservation.setTableNumber(reservationRequest.getTableNumber());

        Reservation savedReservation = reservationRepository.save(reservation);
        return convertToReservationResponse(savedReservation);
    }

    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::convertToReservationResponse)
                .collect(Collectors.toList());
    }

    public ReservationResponse getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with ID: " + id));
        return convertToReservationResponse(reservation);
    }

    public ReservationResponse updateReservation(Long id, ReservationRequest reservationRequest) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with ID: " + id));
        validateTableNumber(reservationRequest.getTableNumber());
        checkTableAvailability(
                reservationRequest.getTableNumber(),
                reservationRequest.getArrivalTime(),
                reservationRequest.getDepartureTime()
        );
        reservation.setClientName(reservationRequest.getClientName());
        reservation.setArrivalTime(reservationRequest.getArrivalTime());
        reservation.setDepartureTime(reservationRequest.getDepartureTime());
        reservation.setNumberOfPeople(reservationRequest.getNumberOfPeople());
        reservation.setTableNumber(reservationRequest.getTableNumber());

        Reservation updatedReservation = reservationRepository.save(reservation);
        return convertToReservationResponse(updatedReservation);
    }

    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with ID: " + id));
        reservationRepository.delete(reservation);
    }

    private void validateTableNumber(Integer tableNumber) {
        if (tableNumber == null || tableNumber < 1 || tableNumber > MAX_TABLES) {
            throw new IllegalArgumentException("Invalid table number. Valid table numbers are between 1 and " + MAX_TABLES);
        }
    }

    private void checkTableAvailability(Integer tableNumber, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        List<Reservation> conflictingReservations = reservationRepository.findAll().stream()
                .filter(reservation -> reservation.getTableNumber().equals(tableNumber) &&
                        ((arrivalTime.isBefore(reservation.getDepartureTime()) && departureTime.isAfter(reservation.getArrivalTime()))))
                .collect(Collectors.toList());

        if (!conflictingReservations.isEmpty()) {
            throw new IllegalStateException("Table " + tableNumber + " is already reserved during the requested time.");
        }
    }

    private ReservationResponse convertToReservationResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getClientName(),
                reservation.getArrivalTime(),
                reservation.getDepartureTime(),
                reservation.getNumberOfPeople(),
                reservation.getTableNumber()
        );
    }
}



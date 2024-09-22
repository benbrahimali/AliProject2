package com.university.restaurant1.admin.repository;

import com.university.restaurant1.admin.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

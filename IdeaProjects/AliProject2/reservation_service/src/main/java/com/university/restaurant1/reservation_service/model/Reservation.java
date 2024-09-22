package com.university.restaurant1.reservation_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private Integer numberOfPeople;
    private Integer tableNumber;


    // Other fields as necessary
}

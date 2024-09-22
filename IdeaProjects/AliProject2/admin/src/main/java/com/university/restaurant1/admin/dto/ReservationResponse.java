package com.university.restaurant1.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {
    private Long id;
    private String clientName;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private Integer numberOfPeople;
    private Integer tableNumber;
}

package com.university.restaurant1.reservation_service.dto;

import java.time.LocalDateTime;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private Long id;
    private String clientName;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private Integer numberOfPeople;
    private Integer tableNumber;



}
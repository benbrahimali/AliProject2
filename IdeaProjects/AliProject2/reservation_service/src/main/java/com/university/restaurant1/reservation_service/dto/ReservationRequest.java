package com.university.restaurant1.reservation_service.dto;

import java.time.LocalDateTime;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ReservationRequest {
    @NotNull
    private String clientName;

    @NotNull
    private LocalDateTime arrivalTime;

    @NotNull
    private LocalDateTime departureTime;

    @NotNull

    private Integer numberOfPeople;

    private Integer tableNumber;

    // Getters and Setters
}


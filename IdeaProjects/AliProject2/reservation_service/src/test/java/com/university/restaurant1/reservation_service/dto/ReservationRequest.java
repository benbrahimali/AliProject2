package com.university.restaurant1.reservation_service.dto;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
@Builder
@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ReservationRequest {
    public class ReservationRequest {
        @NotNull
        private String clientName;

        @NotNull
        private LocalDateTime arrivalTime;

        @NotNull
        private LocalDateTime departureTime;

        @NotNull
        @Min(1)
        private Integer numberOfPeople;

        private Integer tableNumber;

        // Getters and Setters
    }


    // Getters and setters
}

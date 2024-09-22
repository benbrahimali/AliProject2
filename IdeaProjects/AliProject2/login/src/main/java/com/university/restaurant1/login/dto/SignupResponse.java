package com.university.restaurant1.login.dto;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponse {
    private String message;
    private boolean success;

    // Constructor, Getters and Setters
}

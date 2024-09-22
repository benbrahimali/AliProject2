package com.university.restaurant1.login.dto;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private boolean success;
    private String token; // Optional: if you are using JWT tokens

    // Constructor, Getters and Setters
}


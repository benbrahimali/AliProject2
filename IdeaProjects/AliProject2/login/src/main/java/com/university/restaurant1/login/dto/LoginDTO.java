package com.university.restaurant1.login.dto;
import lombok.*;
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LoginDTO {
    private String username;
    private String password;

    // Getters and Setters
}


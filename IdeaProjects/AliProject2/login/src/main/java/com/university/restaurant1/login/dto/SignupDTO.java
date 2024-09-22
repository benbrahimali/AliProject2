package com.university.restaurant1.login.dto;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {
    private String username;
    private String password;
    private String email;

    // Getters and Setters
}

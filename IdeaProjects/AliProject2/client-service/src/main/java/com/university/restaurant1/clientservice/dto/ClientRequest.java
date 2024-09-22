package com.university.restaurant1.clientservice.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
    private String name;
    private String email;
    private String phoneNumber;
}

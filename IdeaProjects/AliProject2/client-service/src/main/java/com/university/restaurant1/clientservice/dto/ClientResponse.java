package com.university.restaurant1.clientservice.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}

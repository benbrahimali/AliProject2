package com.university.restaurant1.plat_de_jour.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private String categoryId; // Assuming you use an ID to refer to categories
}


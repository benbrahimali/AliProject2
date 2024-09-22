package com.university.restaurant1.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Long categoryId;
}

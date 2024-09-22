package com.university.restaurant1.plat_de_jour.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Entity
@Table(name = "platjours")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PlatItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private String categoryId; // Assuming you use an ID to refer to categories
}

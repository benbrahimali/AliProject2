package com.university.restaurant1.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemSalesResponse {
    private Long menuItemId;
    private String menuItemName;
    private Integer quantitySold;
}

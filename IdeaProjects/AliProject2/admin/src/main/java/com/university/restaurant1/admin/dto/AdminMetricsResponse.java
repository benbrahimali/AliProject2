package com.university.restaurant1.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminMetricsResponse {
    private LocalDate date;
    private BigDecimal totalIncome;
    private Long totalReservations;
    private Map<Integer, Long> reservationCountsByTable;
    private List<MenuItemSalesResponse> menuItemsRankedBySales;
}

package com.university.restaurant1.admin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "admin_metrics")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "total_income")
    private BigDecimal totalIncome;

    @Column(name = "total_reservations")
    private Long totalReservations;

    @ElementCollection
    @CollectionTable(name = "reservation_counts_by_table", joinColumns = @JoinColumn(name = "admin_metrics_id"))
    @MapKeyColumn(name = "table_number")
    @Column(name = "count")
    private Map<Integer, Long> reservationCountsByTable;

    @OneToMany(mappedBy = "adminMetrics", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItemSalesRank> menuItemsRankedBySales;
}

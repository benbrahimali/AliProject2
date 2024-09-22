package com.university.restaurant1.admin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "menu_item_sales_rank")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MenuItemSalesRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Column(name = "menu_item_name")
    private String menuItemName;

    @Column(name = "quantity_sold")
    private Integer quantitySold;

    @ManyToOne
    @JoinColumn(name = "admin_metrics_id")
    private AdminMetrics adminMetrics;
}

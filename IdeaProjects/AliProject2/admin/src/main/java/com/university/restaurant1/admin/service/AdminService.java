package com.university.restaurant1.admin.service;

import com.university.restaurant1.admin.dto.*;
import com.university.restaurant1.admin.model.MenuItem;
import com.university.restaurant1.admin.model.AdminMetrics;
import com.university.restaurant1.admin.model.MenuItemSalesRank;
import com.university.restaurant1.admin.model.Reservation;
import com.university.restaurant1.admin.repository.AdminMetricsRepository;
import com.university.restaurant1.admin.repository.MenuItemRepository;
import com.university.restaurant1.admin.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final WebClient.Builder webClientBuilder;
    private final AdminMetricsRepository adminMetricsRepository;
    private final MenuItemRepository menuItemRepository;
    private final ReservationRepository reservationRepository;
    public AdminMetricsResponse generateMetricsForToday() {
        LocalDate today = LocalDate.now();

        BigDecimal totalIncome = calculateTotalIncomeForToday();
        Long totalReservations = calculateTotalReservationsForToday();
        Map<Integer, Long> reservationCountsByTable = calculateReservationCountsByTableForToday();
        List<MenuItemSalesResponse> menuItemsRankedBySales = getMenuItemsRankedBySalesForToday();

        List<MenuItemSalesRank> menuItemSalesRanks = menuItemsRankedBySales.stream()
                .map(response -> {
                    MenuItemSalesRank rank = new MenuItemSalesRank();
                    rank.setMenuItemId(response.getMenuItemId());
                    rank.setQuantitySold(response.getQuantitySold());
                    rank.setMenuItemName(response.getMenuItemName());
                    return rank;
                })
                .collect(Collectors.toList());

        AdminMetrics metrics = new AdminMetrics();
        metrics.setDate(today);
        metrics.setTotalIncome(totalIncome);
        metrics.setTotalReservations(totalReservations);
        metrics.setReservationCountsByTable(reservationCountsByTable);
        metrics.setMenuItemsRankedBySales(menuItemSalesRanks);

        AdminMetrics savedMetrics = adminMetricsRepository.save(metrics);

        menuItemSalesRanks.forEach(rank -> rank.setAdminMetrics(savedMetrics));

        return convertToAdminMetricsResponse(savedMetrics);
    }

    private AdminMetricsResponse convertToAdminMetricsResponse(AdminMetrics metrics) {
        List<MenuItemSalesResponse> menuItemsRankedBySales = metrics.getMenuItemsRankedBySales().stream()
                .map(rank -> new MenuItemSalesResponse(
                        rank.getMenuItemId(),
                        rank.getMenuItemName(),
                        rank.getQuantitySold()
                ))
                .collect(Collectors.toList());

        return new AdminMetricsResponse(
                metrics.getDate(),
                metrics.getTotalIncome(),
                metrics.getTotalReservations(),
                metrics.getReservationCountsByTable(),
                menuItemsRankedBySales
        );
    }


    public List<ReservationResponse> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(reservation -> new ReservationResponse(
                        reservation.getId(),
                        reservation.getClientName(),
                        reservation.getArrivalTime(),
                        reservation.getDepartureTime(),
                        reservation.getNumberOfPeople(),
                        reservation.getTableNumber()
                ))
                .collect(Collectors.toList());
    }


    private BigDecimal calculateTotalIncomeForToday() {
        WebClient webClient = webClientBuilder.build();
        List<ReservationResponse> reservations = webClient.get()
                .uri("http://localhost:8121/api/reservations/today")
                .retrieve()
                .bodyToFlux(ReservationResponse.class)
                .collectList()
                .block();

        return reservations.stream()
                .map(reservation -> BigDecimal.valueOf(reservation.getNumberOfPeople() * 20)) // Example calculation
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Long calculateTotalReservationsForToday() {
        WebClient webClient = webClientBuilder.build();
        List<ReservationResponse> reservations = webClient.get()
                .uri("http://localhost:8121/api/reservations/today")
                .retrieve()
                .bodyToFlux(ReservationResponse.class)
                .collectList()
                .block();

        return (long) reservations.size();
    }

    private Map<Integer, Long> calculateReservationCountsByTableForToday() {
        WebClient webClient = webClientBuilder.build();
        List<ReservationResponse> reservations = webClient.get()
                .uri("http://localhost:8088/api/reservations")
                .retrieve()
                .bodyToFlux(ReservationResponse.class)
                .collectList()
                .block();

        return reservations.stream()
                .collect(Collectors.groupingBy(
                        ReservationResponse::getTableNumber,
                        Collectors.counting()
                ));
    }

    private List<MenuItemSalesResponse> getMenuItemsRankedBySalesForToday() {
        WebClient webClient = webClientBuilder.build();
        List<MenuItemResponse> menuItems = webClient.get()
                .uri("http://localhost:8086/api/menu-items")
                .retrieve()
                .bodyToFlux(MenuItemResponse.class)
                .collectList()
                .block();

        // Example logic for ranking items (mock data)
        return menuItems.stream()
                .map(item -> new MenuItemSalesResponse(item.getId(), item.getName(), 0)) // Replace 0 with actual sales data
                .sorted(Comparator.comparingInt(MenuItemSalesResponse::getQuantitySold).reversed())
                .collect(Collectors.toList());
    }

    public MenuItemResponse createMenuItem(MenuItemRequest menuItemRequest) {
        // Convertir le DTO en entité
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemRequest.getName());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setImageUrl(menuItemRequest.getImageUrl());
        menuItem.setAvailable(true);

        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        // Convertir l'entité en DTO de réponse
        return new MenuItemResponse(
                savedMenuItem.getId(),
                savedMenuItem.getName(),
                savedMenuItem.getDescription(),
                savedMenuItem.getPrice(),
                savedMenuItem.getAvailable()
        );
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}

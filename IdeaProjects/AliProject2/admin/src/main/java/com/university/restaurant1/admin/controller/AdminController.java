package com.university.restaurant1.admin.controller;

import com.university.restaurant1.admin.dto.*;
import com.university.restaurant1.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // **Gestion des éléments de menu**

    @PostMapping("/menu-items")
    @ResponseStatus(HttpStatus.CREATED)  // Utilisation de la bonne annotation de statut HTTP
    public MenuItemResponse createMenuItem(@RequestBody MenuItemRequest menuItemRequest) {
        // Création d'un élément de menu
        return adminService.createMenuItem(menuItemRequest);
    }

    @DeleteMapping("/menu-items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // 204 No Content pour les suppressions
    public void deleteMenuItem(@PathVariable Long id) {
        // Suppression de l'élément de menu
        adminService.deleteMenuItem(id);
    }

    // **Gestion des réservations**



    @GetMapping("/reservations")
    @ResponseStatus(HttpStatus.OK)
  // 200 OK pour les requêtes GET
    public List<ReservationResponse> getAllReservations() {
        // Récupération de toutes les réservations
        return adminService.getAllReservations();
    }

    @GetMapping("/metrics/today")
    @ResponseStatus(HttpStatus.OK)
    public AdminMetricsResponse getMetricsForToday() {
        // Génération des métriques pour aujourd'hui
        return adminService.generateMetricsForToday();
    }

}
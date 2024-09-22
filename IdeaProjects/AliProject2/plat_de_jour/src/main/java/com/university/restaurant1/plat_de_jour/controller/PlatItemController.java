package com.university.restaurant1.plat_de_jour.controller;

import com.university.restaurant1.plat_de_jour.dto.MenuItemRequest;
import com.university.restaurant1.plat_de_jour.dto.MenuItemResponse;
import com.university.restaurant1.plat_de_jour.service.PlatItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class PlatItemController {


    private final PlatItemService platItemService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMenuItem(@RequestBody MenuItemRequest menuItemRequest) {
        platItemService.createMenuItem(menuItemRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItemResponse> getAllMenuItems() {
        return platItemService.getAllMenuItems();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MenuItemResponse getMenuItemById(@PathVariable Long id) {
        return platItemService.getMenuItemById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenuItem(@PathVariable Long id) {
        platItemService.deleteMenuItem(id);
    }
}

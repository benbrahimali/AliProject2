package com.university.restaurant1.plat_de_jour.service;

import com.university.restaurant1.plat_de_jour.dto.MenuItemRequest;
import com.university.restaurant1.plat_de_jour.dto.MenuItemResponse;
import com.university.restaurant1.plat_de_jour.exception.ResourceNotFoundException;
import com.university.restaurant1.plat_de_jour.model.PlatItem;
import com.university.restaurant1.plat_de_jour.repository.PlatItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlatItemService {
    private final PlatItemRepository menuItemRepository;

    public void createMenuItem(MenuItemRequest menuItemRequest) {
        PlatItem platItem = PlatItem.builder()
                .name(menuItemRequest.getName())
                .description(menuItemRequest.getDescription())
                .price(menuItemRequest.getPrice())
                .imageUrl(menuItemRequest.getImageUrl())
                .categoryId(menuItemRequest.getCategoryId())
                .build();

        menuItemRepository.save(platItem);
        log.info("Menu item {} is saved", platItem.getId());
    }

    public List<MenuItemResponse> getAllMenuItems() {
        List<PlatItem> platItems = menuItemRepository.findAll();

        return platItems.stream().map(this::mapToMenuItemResponse).toList();
    }

    private MenuItemResponse mapToMenuItemResponse(PlatItem menuItem) {
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .imageUrl(menuItem.getImageUrl())
                .categoryId(menuItem.getCategoryId())
                .build();
    }
    public MenuItemResponse getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .map(this::mapToMenuItemResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id " + id));
    }
    public void deleteMenuItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Menu item not found with id " + id);
        }
        menuItemRepository.deleteById(id);
        log.info("Menu item with id {} is deleted", id);
    }
}


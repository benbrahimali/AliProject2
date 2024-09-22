package com.university.restaurant1.admin.repository;

import com.university.restaurant1.admin.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}


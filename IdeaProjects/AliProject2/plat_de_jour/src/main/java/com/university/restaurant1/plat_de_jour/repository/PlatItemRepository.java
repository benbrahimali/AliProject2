package com.university.restaurant1.plat_de_jour.repository;

import com.university.restaurant1.plat_de_jour.model.PlatItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatItemRepository extends JpaRepository<PlatItem, Long> {
}


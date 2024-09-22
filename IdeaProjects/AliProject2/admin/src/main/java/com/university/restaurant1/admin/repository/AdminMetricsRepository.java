package com.university.restaurant1.admin.repository;

import com.university.restaurant1.admin.model.AdminMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMetricsRepository extends JpaRepository<AdminMetrics, Long> {
}

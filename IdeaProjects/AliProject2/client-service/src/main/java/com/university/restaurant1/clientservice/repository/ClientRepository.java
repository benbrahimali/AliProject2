package com.university.restaurant1.clientservice.repository;

import com.university.restaurant1.clientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}

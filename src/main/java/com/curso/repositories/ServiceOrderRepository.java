package com.curso.repositories;

import com.curso.domains.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, UUID> {
}

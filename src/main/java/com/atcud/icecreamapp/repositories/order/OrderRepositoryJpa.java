package com.atcud.icecreamapp.repositories.order;

import com.atcud.icecreamapp.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryJpa extends JpaRepository<Order, Long> {
}

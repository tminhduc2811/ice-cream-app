package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Order;

public interface OrderRepository {

    public List<Order> findAll();

    Optional<Order> findById(Long id);

    public Order save(Order onlineOrder);

    public void delete(Order onlineOrder);

    public void update(Order onlineOrder);

}

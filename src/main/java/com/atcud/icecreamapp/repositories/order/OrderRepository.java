package com.atcud.icecreamapp.repositories.order;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.OrderDTO;
import com.atcud.icecreamapp.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepository {

    public Page<Order> findPage(Pageable pageable);

    public List<Order> findAll();

    Optional<Order> findById(Long id);

    public Order save(Order onlineOrder);

    public void delete(Order onlineOrder);

    public void update(Order onlineOrder);

}

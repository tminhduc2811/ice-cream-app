package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.OrderDTO;
import com.atcud.icecreamapp.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    public Page<OrderDTO> findPage(Pageable pageable);

    public List<OrderDTO> getAllOrders();

    public Optional<Order> getOptionalOrderById(Long id);

    public Order save(Order order);

    public void delete(Order order);

    public void update(Order order);

    public OrderDTO getOrderById(Long id);

}

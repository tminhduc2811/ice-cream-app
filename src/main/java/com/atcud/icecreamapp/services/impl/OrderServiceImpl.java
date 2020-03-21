package com.atcud.icecreamapp.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.DTO.OrderDTO;
import com.atcud.icecreamapp.entities.Order;
import com.atcud.icecreamapp.repositories.OrderRepository;
import com.atcud.icecreamapp.services.OrderService;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> entities = orderRepository.findAll();
        List<OrderDTO> orders = new ArrayList<OrderDTO>();
        Iterator<Order> iterator = entities.iterator();

        while (iterator.hasNext()) {
            Order order = iterator.next();
            orders.add(DTOBuilder.orderToDTO(order));
        }
        return orders;
    }

    @Override
    public Optional<Order> getOptionalOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void update(Order order) {
        orderRepository.update(order);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return DTOBuilder.orderToDTO(orderRepository.findById(id).get());
    }

}

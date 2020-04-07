package com.atcud.icecreamapp.services.impl;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.DTO.entities.OrderDTO;
import com.atcud.icecreamapp.entities.Order;
import com.atcud.icecreamapp.repositories.order.OrderRepository;
import com.atcud.icecreamapp.services.OrderService;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<OrderDTO> findPage(Pageable pageable) {
        Page<Order> entityPage = orderRepository.findPage(pageable);
        return DTOBuilder.mapPage(entityPage, OrderDTO.class);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> entities = orderRepository.findAll();
        return DTOBuilder.mapList(entities, OrderDTO.class);
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
        Optional<Order> currentOrder = orderRepository.findById(order.getId());
        if (!currentOrder.isPresent()) {
            throw new CustomException("Order not found", HttpStatus.NOT_FOUND);
        }
        currentOrder.get().setStatus(order.getStatus());
        orderRepository.update(order);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Optional<Order> entity = orderRepository.findById(id);
        if (entity.isPresent()) {
            return DTOBuilder.mapObject(entity.get(), OrderDTO.class);
        }
        throw new CustomException("Order not found", HttpStatus.NOT_FOUND);
    }

}

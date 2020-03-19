package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.OrderDTO;
import com.atcud.icecreamapp.entities.Order;

public interface OrderService {

	public List<OrderDTO> getAllOrders();
	
	public Optional<Order> getOrderById(Long id);
	
	public Order save(Order order);
	
	public void delete(Order order);
	
	public void update(Order order);
}

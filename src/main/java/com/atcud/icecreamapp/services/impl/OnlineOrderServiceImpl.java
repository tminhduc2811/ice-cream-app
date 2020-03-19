package com.atcud.icecreamapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.Order;
import com.atcud.icecreamapp.repositories.OnlineOrderRepository;
import com.atcud.icecreamapp.services.OnlineOrderService;

@Component
public class OnlineOrderServiceImpl implements OnlineOrderService {

	@Autowired
	private OnlineOrderRepository onlineOrderRepository;
	@Override
	public List<Order> getAllOrders() {
		return onlineOrderRepository.findAll();
	}

	@Override
	public Optional<Order> getOrderById(Long id) {
		return onlineOrderRepository.findById(id);
	}

	@Override
	public Order save(Order order) {
		return onlineOrderRepository.save(order);
	}

	@Override
	public void delete(Order order) {		
		onlineOrderRepository.delete(order);
	}

	@Override
	public void update(Order order) {
		onlineOrderRepository.update(order);
	}

}

package com.atcud.icecreamapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.OrderDetail;
import com.atcud.icecreamapp.repositories.OrderDetailRepository;
import com.atcud.icecreamapp.services.OrderDetailService;

@Component
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Override
	public List<OrderDetail> getAllOrderDetails() {
		return orderDetailRepository.findAll();
	}

	@Override
	public Optional<OrderDetail> getOrderDetailById(Long id) {
		return orderDetailRepository.findById(id);
	}

	@Override
	public OrderDetail save(OrderDetail orderDetail) {
		return orderDetailRepository.save(orderDetail);
	}

	@Override
	public void delete(OrderDetail orderDetail) {
		orderDetailRepository.delete(orderDetail);
	}

	@Override
	public void update(OrderDetail orderDetail) {
		orderDetailRepository.update(orderDetail);
	}

}

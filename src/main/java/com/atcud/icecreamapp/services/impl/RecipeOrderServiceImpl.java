package com.atcud.icecreamapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.RecipeOrder;
import com.atcud.icecreamapp.repositories.RecipeOrderRepository;
import com.atcud.icecreamapp.services.RecipeOrderService;

@Component
public class RecipeOrderServiceImpl implements RecipeOrderService {

	@Autowired
	private RecipeOrderRepository onlineOrderRepository;
	@Override
	public List<RecipeOrder> getAllOrders() {
		return onlineOrderRepository.findAll();
	}

	@Override
	public Optional<RecipeOrder> getOrderById(Long id) {
		return onlineOrderRepository.findById(id);
	}

	@Override
	public RecipeOrder save(RecipeOrder order) {
		return onlineOrderRepository.save(order);
	}

	@Override
	public void delete(RecipeOrder order) {		
		onlineOrderRepository.delete(order);
	}

	@Override
	public void update(RecipeOrder order) {
		onlineOrderRepository.update(order);
	}

}

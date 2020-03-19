package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.RecipeOrder;

public interface RecipeOrderService {

	public List<RecipeOrder> getAllOrders();
	
	public Optional<RecipeOrder> getOrderById(Long id);
	
	public RecipeOrder save(RecipeOrder order);
	
	public void delete(RecipeOrder order);
	
	public void update(RecipeOrder order);
}

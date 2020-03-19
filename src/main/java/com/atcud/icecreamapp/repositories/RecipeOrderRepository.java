package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.RecipeOrder;

public interface RecipeOrderRepository {
	
	public List<RecipeOrder> findAll();
	
	public Optional<RecipeOrder> findById(Long id);
	
	public RecipeOrder save(RecipeOrder onlineOrder);
	
	public void delete(RecipeOrder onlineOrder);
	
	public void update(RecipeOrder onlineOrder);
}

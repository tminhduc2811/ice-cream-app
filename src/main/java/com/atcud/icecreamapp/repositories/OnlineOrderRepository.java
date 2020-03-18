package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.OnlineOrder;

public interface OnlineOrderRepository {
	
	public List<OnlineOrder> findAll();
	
	public Optional<OnlineOrder> findById(Long id);
	
	public OnlineOrder save(OnlineOrder onlineOrder);
	
	public void delete(OnlineOrder onlineOrder);
	
}

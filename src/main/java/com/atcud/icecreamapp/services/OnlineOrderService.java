package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.OnlineOrder;

public interface OnlineOrderService {

	public List<OnlineOrder> getAllOrder();
	
	public Optional<OnlineOrder> getOrderkById(Long id);
	
	public OnlineOrder save(OnlineOrder order);
	
	public void delete(OnlineOrder order);
}

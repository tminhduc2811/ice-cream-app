package com.atcud.icecreamapp.DTO;

import com.atcud.icecreamapp.entities.Order;

public class DTOBuilder {

	public static OrderDTO orderToDTO(Order order) {
		return new OrderDTO(order.getId(), 
				order.getCustomer().getId(), 
				order.getPayment().getId(), 
				order.getPaymentOption(), 
				order.getCreatedDate(), 
				order.getDeliveryDetail(), 
				order.getNotes(), 
				order.getStatus());
	}
}

package com.atcud.icecreamapp.DTO;

import com.atcud.icecreamapp.entities.Customer;
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
	
	public static CustomerDTO customerToDTO(Customer customer) {
		return new CustomerDTO(customer.getId(), 
				customer.getUserName(), 
				"", 
				customer.getFirstName(), 
				customer.getLastName(), 
				customer.getAddress(), 
				customer.getPhoneNumber(), 
				customer.getEmail(), 
				customer.getGender(), 
				customer.getBirthday(), 
				customer.getAvatar(), 
				customer.getExpiredDate(), 
				customer.getStatus(), 
				customer.getNumOfLoginFailed());
	}

	public static LoginResponseDTO loginResponseDTO(LoginResponseDTO loginResponseDTO) {
		return new LoginResponseDTO(
				loginResponseDTO.getUserName(),
				loginResponseDTO.getToken()
		);
	}
}

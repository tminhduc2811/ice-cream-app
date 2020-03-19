package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomers();
	
	public Optional<Customer> getCustomerById(Long id);
	
	public Customer save(Customer customer);
	
	public void delete(Customer customer);
	
	public void update(Customer customer);

}

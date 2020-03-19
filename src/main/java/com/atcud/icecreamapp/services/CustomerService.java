package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.entities.UserRecipe;

public interface CustomerService {

	public List<Customer> getAllCustomer();
	
	public Optional<Customer> getCustomerById(Long id);
	
	public Customer save(Customer customer);
	
	public void delete(Customer customer);
	
	public void update(Customer customer);
	
	public List<UserRecipe> getCustomerRecipes(Long id);
	
}

package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.entities.UserRecipe;

public interface CustomerRepository {
	
	public List<Customer> findAll();
	
	public List<UserRecipe> getCustomerRecipes(Long id);
	
	public Optional<Customer> findById(Long id);
	
	public Customer save(Customer customer);
	
	public void delete(Customer customer);
	
	public void update(Customer customer);
	
}

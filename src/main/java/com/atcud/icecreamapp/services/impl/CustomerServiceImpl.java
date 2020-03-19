package com.atcud.icecreamapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.entities.UserRecipe;
import com.atcud.icecreamapp.repositories.CustomerRepository;
import com.atcud.icecreamapp.services.CustomerService;

@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void delete(Customer customer) {
		customerRepository.delete(customer);
	}

	@Override
	public void update(Customer customer) {
		customerRepository.update(customer);
	}

	@Override
	public List<UserRecipe> getCustomerRecipes(Long id) {
		return customerRepository.getCustomerRecipes(id);
	}

}

package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.entities.UserRecipe;
import com.atcud.icecreamapp.repositories.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Customer> findAll() {
		return entityManager.createQuery("FROM Customer", Customer.class).getResultList();
	}

	@Override
	@Transactional
	public Optional<Customer> findById(Long id) {
		return Optional.of(entityManager.find(Customer.class, id));
	}

	@Override
	@Transactional
	public Customer save(Customer customer) {
		entityManager.persist(customer);
		return customer;
	}

	@Override
	@Transactional
	public void delete(Customer customer) {
		entityManager.remove(customer);
	}

	@Override
	@Transactional
	public void update(Customer customer) {
		entityManager.merge(customer);
	}

	@Override
	@Transactional
	public List<UserRecipe> getCustomerRecipes(Long id) {
		Customer customer = entityManager.find(Customer.class, id);
		return customer.getUserRecipes();
	}

}

package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Order;
import com.atcud.icecreamapp.repositories.OrderRepository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Order> findAll() {
		List<Order> orders = entityManager.createQuery("FROM Order", Order.class).getResultList();
		return orders;
	}

	@Override
	@Transactional
	public Optional<Order> findById(Long id) {
		return Optional.of(entityManager.find(Order.class, id));
	}

	@Override
	@Transactional
	public Order save(Order onlineOrder) {
		entityManager.persist(onlineOrder);
		return onlineOrder;
	}

	@Override
	@Transactional
	public void delete(Order onlineOrder) {
		entityManager.remove(onlineOrder);

	}

	@Override
	@Transactional
	public void update(Order onlineOrder) {
		entityManager.merge(onlineOrder);
		
	}

}

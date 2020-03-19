package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Order;
import com.atcud.icecreamapp.repositories.OnlineOrderRepository;

@Repository
public class OnlineOrderRepositoryImpl implements OnlineOrderRepository {

	@Autowired
	EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Order> findAll() {
		return entityManager.createQuery("FROM OnlineOrder", Order.class).getResultList();
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

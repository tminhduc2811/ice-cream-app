package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.OnlineOrder;
import com.atcud.icecreamapp.repositories.OnlineOrderRepository;

@Repository
public class OnlineOrderRepositoryImpl implements OnlineOrderRepository {

	@Autowired
	EntityManager entityManager;
	
	@Override
	@Transactional
	public List<OnlineOrder> findAll() {
		return entityManager.createQuery("FROM OnlineOrder", OnlineOrder.class).getResultList();
	}

	@Override
	@Transactional
	public Optional<OnlineOrder> findById(Long id) {
		return Optional.of(entityManager.find(OnlineOrder.class, id));
	}

	@Override
	@Transactional
	public OnlineOrder save(OnlineOrder onlineOrder) {
		entityManager.persist(onlineOrder);
		return onlineOrder;
	}

	@Override
	@Transactional
	public void delete(OnlineOrder onlineOrder) {
		entityManager.remove(onlineOrder);

	}

	@Override
	@Transactional
	public void update(OnlineOrder onlineOrder) {
		entityManager.merge(onlineOrder);
		
	}

}

package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.RecipeOrder;
import com.atcud.icecreamapp.repositories.RecipeOrderRepository;

@Repository
public class RecipeOrderRepositoryImpl implements RecipeOrderRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public List<RecipeOrder> findAll() {
		List<RecipeOrder> orders = entityManager.createQuery("FROM RecipeOrder", RecipeOrder.class).getResultList();
		return orders;
	}

	@Override
	@Transactional
	public Optional<RecipeOrder> findById(Long id) {
		return Optional.of(entityManager.find(RecipeOrder.class, id));
	}

	@Override
	@Transactional
	public RecipeOrder save(RecipeOrder onlineOrder) {
		entityManager.persist(onlineOrder);
		return onlineOrder;
	}

	@Override
	@Transactional
	public void delete(RecipeOrder onlineOrder) {
		entityManager.remove(onlineOrder);

	}

	@Override
	@Transactional
	public void update(RecipeOrder onlineOrder) {
		entityManager.merge(onlineOrder);
		
	}

}

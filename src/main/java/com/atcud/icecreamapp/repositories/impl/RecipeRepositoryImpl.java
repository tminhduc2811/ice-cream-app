package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Recipe;
import com.atcud.icecreamapp.repositories.RecipeRepository;

@Repository
public class RecipeRepositoryImpl implements RecipeRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Recipe> findAll() {
		return entityManager.createQuery("FROM Recipe", Recipe.class).getResultList();
	}

	@Override
	@Transactional
	public Optional<Recipe> findById(Long id) {
		return Optional.of(entityManager.find(Recipe.class, id));
	}

	@Override
	@Transactional
	public Recipe save(Recipe recipe) {
		entityManager.persist(recipe);
		return recipe;
	}

	@Override
	@Transactional
	public void delete(Recipe recipe) {
		entityManager.remove(recipe);
	}

	@Override
	public void update(Recipe recipe) {
		entityManager.merge(recipe);
	}

}

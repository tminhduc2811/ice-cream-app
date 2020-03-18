package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Recipe;
import com.atcud.icecreamapp.repositories.RecipeRepository;

@Repository
public class RecipeRepositoryImpl implements RecipeRepository {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Recipe> findAll() {
		return entityManager.createQuery("FROM Recipe", Recipe.class).getResultList();
	}

	@Override
	public Optional<Recipe> findById(Long id) {
		return Optional.of(entityManager.find(Recipe.class, id));
	}

	@Override
	public Recipe save(Recipe recipe) {
		entityManager.persist(recipe);
		return recipe;
	}

	@Override
	public void delete(Recipe recipe) {
		entityManager.remove(recipe);
	}

}

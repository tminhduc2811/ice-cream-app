package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.atcud.icecreamapp.repositories.recipe.RecipeRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Recipe;
import com.atcud.icecreamapp.repositories.recipe.RecipeRepository;

@Repository
public class RecipeRepositoryImpl implements RecipeRepository {

    @Autowired
    @Lazy
    RecipeRepositoryJpa recipeRepositoryJpa;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<Recipe> findPage(Pageable pageable) {
        return recipeRepositoryJpa.findAll(pageable);
    }

    @Override
    @Transactional
    public List<Recipe> findAll() {
        return recipeRepositoryJpa.findAll();
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

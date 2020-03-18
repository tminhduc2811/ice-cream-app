package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Recipe;

public interface RecipeRepository {
	
	public List<Recipe> findAll();
	
	public Optional<Recipe> findById(Long id);
	
	public Recipe save(Recipe recipe);
	
	public void delete(Recipe recipe);
	
}

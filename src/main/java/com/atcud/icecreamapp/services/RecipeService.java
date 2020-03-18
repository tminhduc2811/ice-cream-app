package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Recipe;

public interface RecipeService {
	
	public List<Recipe> getAllRecipes();
	
	public Optional<Recipe> getRecipeById(Long id);
	
	public Recipe save(Recipe recipe);
	
	public void delete(Recipe recipe);
	
	public void update(Recipe recipe);
	
}

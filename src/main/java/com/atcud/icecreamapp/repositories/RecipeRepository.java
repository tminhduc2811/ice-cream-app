package com.atcud.icecreamapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atcud.icecreamapp.entities.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	
}

package com.atcud.icecreamapp.repositories;

import com.atcud.icecreamapp.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepositoryJpa extends JpaRepository<Recipe, Long> {

}

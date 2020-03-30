package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository {

    public List<Recipe> findAll();

    public Optional<Recipe> findById(Long id);

    public Recipe save(Recipe recipe);

    public void delete(Recipe recipe);

    public void update(Recipe recipe);

}


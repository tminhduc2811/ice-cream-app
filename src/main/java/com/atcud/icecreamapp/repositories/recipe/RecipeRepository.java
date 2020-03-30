package com.atcud.icecreamapp.repositories.recipe;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecipeRepository {

    public Page<Recipe> findPage(Pageable pageable);

    public List<Recipe> findAll();

    public Optional<Recipe> findById(Long id);

    public Recipe save(Recipe recipe);

    public void delete(Recipe recipe);

    public void update(Recipe recipe);

}


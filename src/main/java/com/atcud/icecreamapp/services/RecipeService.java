package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.RecipeDTO;
import com.atcud.icecreamapp.entities.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecipeService {

    public Page<RecipeDTO> findPage(Pageable pageable);

    public List<RecipeDTO> getAllRecipes();

    public Optional<Recipe> getRecipeById(Long id);

    public Recipe save(Recipe recipe);

    public void delete(Recipe recipe);

    public void update(Recipe recipe);

}

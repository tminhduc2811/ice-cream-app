package com.atcud.icecreamapp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.DTO.entities.RecipeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.Recipe;
import com.atcud.icecreamapp.repositories.recipe.RecipeRepository;
import com.atcud.icecreamapp.services.RecipeService;

@Component
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Page<RecipeDTO> findPage(Pageable pageable) {
        Page<Recipe> entityPage = recipeRepository.findPage(pageable);

        return DTOBuilder.mapEntityPageIntoDtoPage(entityPage, RecipeDTO.class);
    }

    @Override
    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        List<RecipeDTO> result = new ArrayList<>();
        for (Recipe recipe : recipes) {
            result.add(DTOBuilder.recipeToDTO(recipe));
        }
        return result;
    }

    @Override
    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public void delete(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    @Override
    public void update(Recipe recipe) {
        recipeRepository.update(recipe);
    }

}

package com.atcud.icecreamapp.services.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.DTO.entities.RecipeDTO;
import com.atcud.icecreamapp.entities.Icecream;
import com.atcud.icecreamapp.entities.User;
import com.atcud.icecreamapp.exceptions.CustomException;
import com.atcud.icecreamapp.repositories.IcecreamRepository;
import com.atcud.icecreamapp.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.Recipe;
import com.atcud.icecreamapp.repositories.recipe.RecipeRepository;
import com.atcud.icecreamapp.services.RecipeService;

@Component
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IcecreamRepository icecreamRepository;

    @Override
    public Page<RecipeDTO> findPage(Pageable pageable) {
        Page<Recipe> entityPage = recipeRepository.findPage(pageable);
        return DTOBuilder.mapPage(entityPage, RecipeDTO.class);
    }

    @Override
    public List<RecipeDTO> getAllRecipes() {
        return DTOBuilder.mapList(recipeRepository.findAll(), RecipeDTO.class);
    }

    @Override
    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public Recipe save(Recipe recipe) {
        User user = userRepository.findUserByUsername(recipe.getUser().getUserName());
        if (user == null) {
            throw new CustomException("User not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        recipe.setViewCount(0);
        recipe.setUploadedDate(new Timestamp(System.currentTimeMillis()));
        recipe.setUser(user);
        Icecream icecream;
        if (recipe.getIcecream() == null || recipe.getIcecream().getId() == null) {
            icecream = icecreamRepository.findById(4L).get();
        } else {
            icecream = icecreamRepository.findById(recipe.getIcecream().getId()).get();
        }
        recipe.setIcecream(icecream);
        return recipeRepository.save(recipe);
    }

    @Override
    public void delete(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    @Override
    public void update(Recipe recipe) {
        User user = userRepository.findUserByUsername(recipe.getUser().getUserName());
        if (user == null) {
            throw new CustomException("User not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        recipe.setUser(user);
        recipeRepository.update(recipe);
        Icecream icecream;
        if (recipe.getIcecream() == null || recipe.getIcecream().getId() == null) {
            icecream = icecreamRepository.findById(4L).get();
        } else {
            icecream = icecreamRepository.findById(recipe.getIcecream().getId()).get();
        }
        recipe.setIcecream(icecream);
    }

}

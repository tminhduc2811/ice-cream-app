package com.atcud.icecreamapp.controllers;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.RecipeDTO;
import com.atcud.icecreamapp.services.IcecreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.atcud.icecreamapp.entities.Recipe;
import com.atcud.icecreamapp.services.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IcecreamService icecreamService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<RecipeDTO>> getRecipes(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        } else if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        assert sortable != null;
        Pageable pageable = PageRequest.of(page, size, sortable);
        return new ResponseEntity<>(recipeService.findPage(pageable), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(recipeService.save(recipe), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.getRecipeById(id);
        if (!recipe.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipeService.delete(recipe.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Recipe> update(@RequestBody Recipe newRecipe, @PathVariable Long id) {
        Optional<Recipe> currentRecipe = recipeService.getRecipeById(id);
        if (!currentRecipe.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipeService.update(newRecipe);
        return new ResponseEntity<>(newRecipe, HttpStatus.OK);
    }

    @RequestMapping(value = "/type/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<RecipeDTO>> getAllRecipesByType(@PathVariable Long id) {
        return new ResponseEntity<>(icecreamService.getAllRecipeByIcecreamId(id), HttpStatus.OK);
    }
}

package com.atcud.icecreamapp.controllers;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.RecipeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atcud.icecreamapp.entities.Recipe;
import com.atcud.icecreamapp.services.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
	@Autowired
	private RecipeService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
		List<RecipeDTO> recipes = service.getAllRecipes();
		if (recipes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(recipes, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json" )
	public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
		return new ResponseEntity<>(service.save(recipe), HttpStatus.CREATED);
	}	
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Recipe> deleteRecipe(@PathVariable Long id){
		Optional<Recipe> recipe = service.getRecipeById(id);
		if (!recipe.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		service.delete(recipe.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces="application/json" )
	public ResponseEntity<Recipe> update(@RequestBody Recipe newRecipe, @PathVariable Long id) {
		Optional<Recipe> currentRecipe = service.getRecipeById(id);
		if (!currentRecipe.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		service.update(newRecipe);
		return new ResponseEntity<>(newRecipe, HttpStatus.OK);
	}
}

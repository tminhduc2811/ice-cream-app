package com.atcud.icecreamapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atcud.icecreamapp.entities.Icecream;
import com.atcud.icecreamapp.entities.Recipe;
import com.atcud.icecreamapp.services.IcecreamService;

@RestController
@RequestMapping("/icecream")
public class IcecreamController {

	@Autowired
	IcecreamService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Icecream>> getAllIcecream() {
		List<Icecream> icecream = service.getAllIcecreams();
		return new ResponseEntity<List<Icecream>>(icecream, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/recipes", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Recipe>> getRecipesofIcecream(@PathVariable Long id) {
		List<Recipe> recipes = service.getAllRecipeByIcecreamId(id);
		return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json" )
	public ResponseEntity<Icecream> createIcecream(@RequestBody Icecream icecream) {
		icecream = service.save(icecream);
		return new ResponseEntity<Icecream>(icecream, HttpStatus.CREATED);
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces="application/json" )
	public ResponseEntity<Icecream> deleteOrder(@PathVariable Long id){
		Optional<Icecream> icecream = service.getIcecreamById(id);
		if (!icecream.isPresent()) {
			return new ResponseEntity<Icecream>(HttpStatus.NOT_FOUND);
		}
		service.delete(icecream.get());
		return new ResponseEntity<Icecream>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces="application/json" )
	public ResponseEntity<Icecream> update(@RequestBody Icecream newIcecream, @PathVariable Long id) {
		Optional<Icecream> order = service.getIcecreamById(id);
		if (!order.isPresent()) {
			return new ResponseEntity<Icecream>(HttpStatus.NOT_FOUND);
		}
		service.update(newIcecream);
		return new ResponseEntity<Icecream>(newIcecream, HttpStatus.OK);
	}
}

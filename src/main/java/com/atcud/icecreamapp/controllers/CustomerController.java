package com.atcud.icecreamapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.entities.UserRecipe;
import com.atcud.icecreamapp.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@RequestMapping(value = "/{id}/recipes", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<UserRecipe>> getCustomerRecipes(@PathVariable Long id) {
		List<UserRecipe> recipes = service.getCustomerRecipes(id);
		if (recipes.isEmpty()) {
			return new ResponseEntity<List<UserRecipe>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserRecipe>>(recipes, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Customer>> getCustomerCustomers() {
		List<Customer> customers = service.getAllCustomer();
		if (customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

}
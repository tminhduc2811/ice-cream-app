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

import com.atcud.icecreamapp.entities.RecipeOrder;
import com.atcud.icecreamapp.services.RecipeOrderService;

@RestController
@RequestMapping("/orders")
public class RecipeOrderController {
	@Autowired
	private RecipeOrderService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<RecipeOrder>> getAllOrder() {
		List<RecipeOrder> orders = service.getAllOrders();
		System.out.println(orders);
		return new ResponseEntity<List<RecipeOrder>>(orders, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json" )
	public ResponseEntity<RecipeOrder> createFAQ(@RequestBody RecipeOrder order) {
		order = service.save(order);
		return new ResponseEntity<RecipeOrder>(order, HttpStatus.CREATED);
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces="application/json" )
	public ResponseEntity<RecipeOrder> deleteOrder(@PathVariable Long id){
		Optional<RecipeOrder> feedback = service.getOrderById(id);
		if (!feedback.isPresent()) {
			return new ResponseEntity<RecipeOrder>(HttpStatus.NOT_FOUND);
		}
		service.delete(feedback.get());
		return new ResponseEntity<RecipeOrder>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces="application/json" )
	public ResponseEntity<RecipeOrder> update(@RequestBody RecipeOrder newOrder, @PathVariable Long id) {
		Optional<RecipeOrder> order = service.getOrderById(id);
		if (!order.isPresent()) {
			return new ResponseEntity<RecipeOrder>(HttpStatus.NOT_FOUND);
		}
		service.update(newOrder);
		return new ResponseEntity<RecipeOrder>(newOrder, HttpStatus.OK);
	}
}

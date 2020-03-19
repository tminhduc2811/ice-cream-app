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

import com.atcud.icecreamapp.DTO.OrderDTO;
import com.atcud.icecreamapp.entities.Order;
import com.atcud.icecreamapp.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<OrderDTO>> getAllOrder() {
		List<OrderDTO> orders = service.getAllOrders();
		return new ResponseEntity<List<OrderDTO>>(orders, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json" )
	public ResponseEntity<Order> createFAQ(@RequestBody Order order) {
		order = service.save(order);
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces="application/json" )
	public ResponseEntity<Order> deleteOrder(@PathVariable Long id){
		Optional<Order> feedback = service.getOptionalOrderById(id);
		if (!feedback.isPresent()) {
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		}
		service.delete(feedback.get());
		return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces="application/json" )
	public ResponseEntity<Order> update(@RequestBody Order newOrder, @PathVariable Long id) {
		Optional<Order> order = service.getOptionalOrderById(id);
		if (!order.isPresent()) {
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		}
		service.update(newOrder);
		return new ResponseEntity<Order>(newOrder, HttpStatus.OK);
	}
}

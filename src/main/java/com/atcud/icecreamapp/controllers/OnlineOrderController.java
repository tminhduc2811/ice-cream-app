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

import com.atcud.icecreamapp.entities.OnlineOrder;
import com.atcud.icecreamapp.services.OnlineOrderService;

@RestController
@RequestMapping("/orders")
public class OnlineOrderController {
	@Autowired
	private OnlineOrderService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<OnlineOrder>> getAllOrder() {
		List<OnlineOrder> order = service.getAllOrders();
		return new ResponseEntity<List<OnlineOrder>>(order, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json" )
	public ResponseEntity<OnlineOrder> createFAQ(@RequestBody OnlineOrder order) {
		order = service.save(order);
		return new ResponseEntity<OnlineOrder>(order, HttpStatus.CREATED);
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces="application/json" )
	public ResponseEntity<OnlineOrder> deleteOrder(@PathVariable Long id){
		Optional<OnlineOrder> feedback = service.getOrderById(id);
		if (!feedback.isPresent()) {
			return new ResponseEntity<OnlineOrder>(HttpStatus.NOT_FOUND);
		}
		service.delete(feedback.get());
		return new ResponseEntity<OnlineOrder>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces="application/json" )
	public ResponseEntity<OnlineOrder> update(@RequestBody OnlineOrder newOrder, @PathVariable Long id) {
		Optional<OnlineOrder> order = service.getOrderById(id);
		if (!order.isPresent()) {
			return new ResponseEntity<OnlineOrder>(HttpStatus.NOT_FOUND);
		}
		service.update(newOrder);
		return new ResponseEntity<OnlineOrder>(newOrder, HttpStatus.OK);
	}
}

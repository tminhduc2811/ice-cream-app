package com.atcud.icecreamapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Customer>> getCustomerCustomers() {
		List<Customer> customers = service.getAllCustomers();
		if (customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json" )
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(hashPassword);
		Customer result = service.save(customer);
		return new ResponseEntity<Customer>(result, HttpStatus.CREATED);
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces="application/json" )
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id){
		Optional<Customer> customer = service.getCustomerById(id);
		if (!customer.isPresent()) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		service.delete(customer.get());
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces="application/json" )
	public ResponseEntity<Customer> update(@RequestBody Customer customer, @PathVariable Long id) {
		Optional<Customer> currentCustomer = service.getCustomerById(id);
		if (!currentCustomer.isPresent()) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if (customer.getPassword() == "") {
			customer.setPassword(currentCustomer.get().getPassword());
		} else {
			customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		}
		service.update(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
}

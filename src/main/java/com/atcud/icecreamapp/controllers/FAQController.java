package com.atcud.icecreamapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atcud.icecreamapp.entities.FAQ;
import com.atcud.icecreamapp.services.FAQService;

@RestController
@RequestMapping("/faq")
public class FAQController {

	//TODO: Add Exception Class to handle all exceptions
	@Autowired
	private FAQService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<FAQ>> readAll() {
		List<FAQ> FAQList = service.getAllFAQ();
		System.out.println(FAQList);
		return new ResponseEntity<List<FAQ>>(FAQList, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json" )
	public ResponseEntity<FAQ> createFAQ(@RequestBody FAQ newFAQ) {
		try {
			
			service.createFAQ(newFAQ);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception ex) {
			return new ResponseEntity<FAQ>(newFAQ, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE, produces="application/json" )
	public ResponseEntity<FAQ> deleteFAQ(@PathVariable int id){
		try {
			service.deleteFAQ(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT, produces="application/json" )
	public ResponseEntity<FAQ> update(@RequestBody FAQ newFAQ) {
		try {
			service.updateFAQ(newFAQ);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

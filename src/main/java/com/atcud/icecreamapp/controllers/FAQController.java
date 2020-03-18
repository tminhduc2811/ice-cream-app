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

import com.atcud.icecreamapp.entities.FAQ;
import com.atcud.icecreamapp.services.FAQService;

@RestController
@RequestMapping("/faq")
public class FAQController {

	//TODO: Add Exception Class to handle all exceptions
	@Autowired
	private FAQService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<FAQ>> getAllFAQ() {
		List<FAQ> FAQList = service.getAllFAQ();
		if (FAQList.isEmpty()) {
			return new ResponseEntity<List<FAQ>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FAQ>>(FAQList, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json" )
	public ResponseEntity<FAQ> createFAQ(@RequestBody FAQ newFAQ) {
		FAQ faq = service.save(newFAQ);
		return new ResponseEntity<FAQ>(faq, HttpStatus.CREATED);
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces="application/json" )
	public ResponseEntity<FAQ> deleteFAQ(@PathVariable Long id){
		Optional<FAQ> faq = service.getFAQById(id);
		if (!faq.isPresent()) {
			return new ResponseEntity<FAQ>(HttpStatus.NOT_FOUND);
		}
		service.delete(faq.get());
		return new ResponseEntity<FAQ>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces="application/json" )
	public ResponseEntity<FAQ> update(@RequestBody FAQ newFAQ, @PathVariable Long id) {
		Optional<FAQ> currentFAQ = service.getFAQById(id);
		if (!currentFAQ.isPresent()) {
			return new ResponseEntity<FAQ>(HttpStatus.NOT_FOUND);
		}
		service.update(newFAQ);
		return new ResponseEntity<FAQ>(newFAQ, HttpStatus.OK);
	}
}

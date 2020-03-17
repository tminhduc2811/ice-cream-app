package com.atcud.icecreamapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atcud.icecreamapp.entities.FAQ;
import com.atcud.icecreamapp.services.FAQService;

@RestController
@RequestMapping("/faq")
public class FAQController {

	@Autowired
	private FAQService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<FAQ>> readAll() {
		List<FAQ> FAQList = service.getAllFAQ();
		System.out.println(FAQList);
		return new ResponseEntity<List<FAQ>>(FAQList, HttpStatus.OK);
	}
	
}

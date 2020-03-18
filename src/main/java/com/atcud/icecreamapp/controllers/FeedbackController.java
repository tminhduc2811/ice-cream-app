package com.atcud.icecreamapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atcud.icecreamapp.entities.Feedback;
import com.atcud.icecreamapp.services.FeedbackServiceImpl;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	//TODO: Implement other handling methods
	@Autowired
	private FeedbackServiceImpl service;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Feedback>> readAllFeedback() {
		List<Feedback> feedback = service.getAllFeedback();
		return new ResponseEntity<List<Feedback>>(feedback, HttpStatus.OK);
	}
}

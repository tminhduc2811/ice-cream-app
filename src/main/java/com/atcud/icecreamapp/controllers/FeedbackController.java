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

import com.atcud.icecreamapp.entities.Feedback;
import com.atcud.icecreamapp.services.FeedBackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Autowired
	private FeedBackService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Feedback>> readAllFeedback() {
		List<Feedback> feedback = service.getAllFeedback();
		return new ResponseEntity<List<Feedback>>(feedback, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json" )
	public ResponseEntity<Feedback> createFAQ(@RequestBody Feedback feedback) {
		feedback = service.save(feedback);
		return new ResponseEntity<Feedback>(feedback, HttpStatus.CREATED);
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces="application/json" )
	public ResponseEntity<Feedback> deleteFeedback(@PathVariable Long id){
		Optional<Feedback> feedback = service.getFeedbackById(id);
		if (!feedback.isPresent()) {
			return new ResponseEntity<Feedback>(HttpStatus.NOT_FOUND);
		}
		service.delete(feedback.get());
		return new ResponseEntity<Feedback>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces="application/json" )
	public ResponseEntity<Feedback> update(@RequestBody Feedback newFeedback, @PathVariable Long id) {
		Optional<Feedback> feedback = service.getFeedbackById(id);
		if (!feedback.isPresent()) {
			return new ResponseEntity<Feedback>(HttpStatus.NOT_FOUND);
		}
		feedback.get().setContent(newFeedback.getContent());
		feedback.get().setFullName(newFeedback.getFullName());
		feedback.get().setTitle(newFeedback.getTitle());
		service.save(feedback.get());
		return new ResponseEntity<Feedback>(feedback.get(), HttpStatus.OK);
	}
}

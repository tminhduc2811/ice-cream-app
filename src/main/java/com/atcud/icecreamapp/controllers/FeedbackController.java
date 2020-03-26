package com.atcud.icecreamapp.controllers;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.FeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atcud.icecreamapp.entities.Feedback;
import com.atcud.icecreamapp.services.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    //TODO: Test these controllers later.
    @Autowired
    private FeedbackService service;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedback() {
        List<FeedbackDTO> feedback = service.getAllFeedback();
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Feedback> createFAQ(@RequestBody Feedback feedback) {
        feedback = service.save(feedback);
        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Feedback> deleteFeedback(@PathVariable Long id) {
        Optional<Feedback> feedback = service.getFeedbackById(id);
        if (!feedback.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(feedback.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Feedback> update(@RequestBody Feedback newFeedback, @PathVariable Long id) {
        Optional<Feedback> feedback = service.getFeedbackById(id);
        if (!feedback.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.update(newFeedback);
        return new ResponseEntity<>(newFeedback, HttpStatus.OK);
    }
}

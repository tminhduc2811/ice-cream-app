package com.atcud.icecreamapp.controllers;

import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.FeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.atcud.icecreamapp.entities.Feedback;
import com.atcud.icecreamapp.services.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    //TODO: Test these controllers later.
    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<FeedbackDTO>> getAllFeedback(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {

        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        } else if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        assert sortable != null;
        Pageable pageable = PageRequest.of(page, size, sortable);
        return new ResponseEntity<>(feedbackService.findPage(pageable), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Feedback> createFAQ(@RequestBody Feedback feedback) {
        feedback = feedbackService.save(feedback);
        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Feedback> deleteFeedback(@PathVariable Long id) {
        Optional<Feedback> feedback = feedbackService.getFeedbackById(id);
        if (!feedback.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        feedbackService.delete(feedback.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Feedback> update(@RequestBody Feedback newFeedback, @PathVariable Long id) {
        Optional<Feedback> feedback = feedbackService.getFeedbackById(id);
        if (!feedback.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        feedbackService.update(newFeedback);
        return new ResponseEntity<>(newFeedback, HttpStatus.OK);
    }
}

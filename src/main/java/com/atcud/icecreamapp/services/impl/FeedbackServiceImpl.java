package com.atcud.icecreamapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.Feedback;
import com.atcud.icecreamapp.repositories.FeedbackRepository;
import com.atcud.icecreamapp.services.FeedBackService;

@Component
public class FeedbackServiceImpl implements FeedBackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Override
	public List<Feedback> getAllFeedback() {
		return feedbackRepository.findAll();
	}

	@Override
	public Optional<Feedback> getFeedbackById(Long id) {
		return feedbackRepository.findById(id);
	}

	@Override
	public Feedback save(Feedback feedback) {		
		return feedbackRepository.save(feedback);
	}

	@Override
	public void delete(Feedback feedback) {
		feedbackRepository.delete(feedback);
	}

	@Override
	public void update(Feedback feedback) {
		feedbackRepository.update(feedback);
	}

}

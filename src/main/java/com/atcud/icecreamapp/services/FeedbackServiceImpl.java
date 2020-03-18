package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.Feedback;
import com.atcud.icecreamapp.repositories.FeedbackRepository;

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
	public void save(Feedback feedback) {		
		feedbackRepository.save(feedback);
	}

	@Override
	public void remove(Feedback feedback) {
		feedbackRepository.delete(feedback);
	}

}

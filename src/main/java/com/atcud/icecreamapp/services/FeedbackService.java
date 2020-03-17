package com.atcud.icecreamapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.Feedback;
import com.atcud.icecreamapp.repositories.FeedbackRepository;

@Component
public class FeedbackService implements IFeedBackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Override
	public List<Feedback> getAllFeedback() {
		return feedbackRepository.getAllFeedback();
	}

	@Override
	public void deleteFeedback(int id) {
		feedbackRepository.deleteFeedback(id);
	}

	@Override
	public void createFeedback(Feedback feedback) {
		feedbackRepository.createFeedback(feedback);
	}

}

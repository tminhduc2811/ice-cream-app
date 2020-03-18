package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Feedback;

public interface FeedBackService {

	public List<Feedback> getAllFeedback();
	
	public Optional<Feedback> getFeedbackById(Long id);
	
	public void save(Feedback feedback);
	
	public void remove(Feedback feedback);
	
}

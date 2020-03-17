package com.atcud.icecreamapp.repositories;

import java.util.List;

import com.atcud.icecreamapp.entities.Feedback;

public interface IFeedbackRepository {
	public List<Feedback> getAllFeedback();
	
	public Feedback getFeedback(int id);
	
	public void deleteFeedback(int id);
	
	public void createFeedback(Feedback feedback);
	
}

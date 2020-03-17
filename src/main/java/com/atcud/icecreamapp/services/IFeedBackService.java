package com.atcud.icecreamapp.services;

import java.util.List;

import com.atcud.icecreamapp.entities.Feedback;

public interface IFeedBackService {

	public List<Feedback> getAllFeedback();
	
	public void deleteFeedback(int id);
	
	public void createFeedback(Feedback feedback);
	
}

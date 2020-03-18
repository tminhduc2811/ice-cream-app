package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Feedback;

public interface FeedbackRepository {
	
	public List<Feedback> findAll();
	
	public Optional<Feedback> findById(Long id);
	
	public Feedback save(Feedback feedback);
	
	public void delete(Feedback feedback);
	
}

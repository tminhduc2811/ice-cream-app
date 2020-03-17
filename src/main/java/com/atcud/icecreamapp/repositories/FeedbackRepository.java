package com.atcud.icecreamapp.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Feedback;

@Repository
public class FeedbackRepository implements IFeedbackRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Feedback> getAllFeedback() {
		List<Feedback> feedbackList = entityManager.createQuery("FROM Feedback", Feedback.class).getResultList();
		return feedbackList;
	}

	@Override
	public void deleteFeedback(int id) {
		
		Feedback feedback = this.getFeedback(id);
		entityManager.merge(feedback);

	}

	@Override
	public Feedback getFeedback(int id) {
		return entityManager.find(Feedback.class, id);
	}

	@Override
	public void createFeedback(Feedback feedback) {
		entityManager.persist(feedback);
	}

}

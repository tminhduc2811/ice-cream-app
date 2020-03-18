package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Feedback;
import com.atcud.icecreamapp.repositories.FeedbackRepository;

@Repository
public class FeedbackRepositoryImpl implements FeedbackRepository {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Feedback> findAll() {
		return entityManager.createQuery("FROM Feedback", Feedback.class).getResultList();
	}

	@Override
	public Optional<Feedback> findById(Long id) {
		return Optional.of(entityManager.find(Feedback.class, id));
	}

	@Override
	public Feedback save(Feedback feedback) {
		entityManager.persist(feedback);
		return feedback;
	}

	@Override
	public void delete(Feedback feedback) {
		entityManager.remove(feedback);
	}

}

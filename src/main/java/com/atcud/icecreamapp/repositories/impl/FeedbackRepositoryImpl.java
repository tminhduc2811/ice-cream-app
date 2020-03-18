package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Feedback;
import com.atcud.icecreamapp.repositories.FeedbackRepository;

@Repository
public class FeedbackRepositoryImpl implements FeedbackRepository {

	@Autowired
	EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Feedback> findAll() {
		return entityManager.createQuery("FROM Feedback", Feedback.class).getResultList();
	}

	@Override
	@Transactional
	public Optional<Feedback> findById(Long id) {
		return Optional.of(entityManager.find(Feedback.class, id));
	}

	@Override
	@Transactional
	public Feedback save(Feedback feedback) {
		entityManager.persist(feedback);
		return feedback;
	}

	@Override
	@Transactional
	public void delete(Feedback feedback) {
		entityManager.remove(feedback);
	}

	@Override
	@Transactional
	public void update(Feedback feedback) {
		entityManager.merge(feedback);
	}

}

package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.FAQ;
import com.atcud.icecreamapp.repositories.FAQRepository;

@Repository
public class FAQRepositoryImpl implements FAQRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public List<FAQ> findAll() {
		return entityManager.createQuery("FROM FAQ", FAQ.class).getResultList();
	}

	@Override
	@Transactional
	public Optional<FAQ> findById(Long id) {
		return Optional.of(entityManager.find(FAQ.class, id));
	}

	@Override
	@Transactional
	public FAQ save(FAQ faq) {
		entityManager.persist(faq);
		return faq;
	}

	@Override
	@Transactional
	public void delete(FAQ faq) {
		entityManager.remove(faq);

	}

	@Override
	public void update(FAQ faq) {
		entityManager.merge(faq);
	}

}

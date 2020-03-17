package com.atcud.icecreamapp.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.FAQ;

@Repository
public class FAQRepository implements IFAQRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public List<FAQ> getAllFAQ() {
		
		List<FAQ> FAQList = entityManager.createQuery("FROM FAQ", FAQ.class).getResultList();
		return FAQList;
	}

	@Override
	@Transactional
	public void createFAQ(FAQ newFAQ) {
		entityManager.persist(newFAQ);
		
	}

	@Override
	@Transactional
	public void deleteFAQ(int id) {
		
		FAQ currentFAQ = this.getFAQ(id);
		entityManager.remove(currentFAQ);
		
	}

	@Override
	@Transactional
	public FAQ getFAQ(int id) {
		return entityManager.find(FAQ.class, id);
	}

	@Override
	@Transactional
	public void updateFAQ(FAQ newFAQ) {
		entityManager.merge(newFAQ);
		
	}

}

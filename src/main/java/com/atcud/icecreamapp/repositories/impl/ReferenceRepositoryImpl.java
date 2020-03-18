package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Reference;
import com.atcud.icecreamapp.repositories.ReferenceRepository;

@Repository
public class ReferenceRepositoryImpl implements ReferenceRepository {

	@Autowired
	EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Reference> findAll() {
		return entityManager.createQuery("FROM Reference", Reference.class).getResultList();
	}

	@Override
	@Transactional
	public Optional<Reference> findById(Long id) {
		return Optional.of(entityManager.find(Reference.class, id));
	}

	@Override
	@Transactional
	public Reference save(Reference reference) {
		entityManager.persist(reference);
		return reference;
	}

	@Override
	@Transactional
	public void delete(Reference reference) {
		entityManager.remove(reference);
	}

	@Override
	public void update(Reference reference) {
		entityManager.merge(reference);
	}

}

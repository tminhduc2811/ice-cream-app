package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Reference;
import com.atcud.icecreamapp.repositories.ReferenceRepository;

@Repository
public class ReferenceRepositoryImpl implements ReferenceRepository {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Reference> findAll() {
		return entityManager.createQuery("FROM Reference", Reference.class).getResultList();
	}

	@Override
	public Optional<Reference> findById(Long id) {
		return Optional.of(entityManager.find(Reference.class, id));
	}

	@Override
	public Reference save(Reference reference) {
		entityManager.persist(reference);
		return reference;
	}

	@Override
	public void delete(Reference reference) {
		entityManager.remove(reference);
	}

}

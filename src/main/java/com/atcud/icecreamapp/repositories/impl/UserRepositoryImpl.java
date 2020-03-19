package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.User;
import com.atcud.icecreamapp.repositories.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<User> findAll() {
		return entityManager.createQuery("FROM User", User.class).getResultList();
	}

	@Override
	public Optional<User> findById(Long id) {
		return Optional.of(entityManager.find(User.class, id));
	}

	@Override
	public User save(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public void delete(User user) {
		entityManager.remove(user);
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}

}

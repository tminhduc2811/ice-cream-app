package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.User;
import com.atcud.icecreamapp.repositories.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public List<User> findAll() {
		return entityManager.createQuery("FROM User", User.class).getResultList();
	}

	@Override
	@Transactional
	public Optional<User> findById(Long id) {
		return Optional.of(entityManager.find(User.class, id));
	}

	@Override
	public boolean isExist(String userName) {
		return false;
	}

	@Override
	@Transactional
	public User findUserByUsername(String userName) {
//		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<User> query = builder.createQuery(User.class);
//		Root<User> root = query.from(User.class);
//		query.select(root).where(builder.equal(root.get("userName"), userName));
//
		User user = entityManager.createQuery("SELECT c FROM User c WHERE c.userName = '" + userName + "'", User.class).getSingleResult();
		return user;
	}

	@Override
	@Transactional
	public User save(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	@Transactional
	public void delete(User user) {
		entityManager.remove(user);
	}

	@Override
	@Transactional
	public void update(User user) {
		entityManager.merge(user);
	}

}

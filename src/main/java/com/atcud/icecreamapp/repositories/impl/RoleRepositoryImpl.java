package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Role;
import com.atcud.icecreamapp.repositories.RoleRepository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Role> findAll() {
		return entityManager.createQuery("FROM Role", Role.class).getResultList();
	}

	@Override
	@Transactional
	public Optional<Role> findById(Long id) {
		return Optional.of(entityManager.find(Role.class, id));
	}

	@Override
	@Transactional
	public Role save(Role role) {
		entityManager.persist(role);
		return role;
	}

	@Override
	@Transactional
	public void delete(Role role) {
		entityManager.remove(role);
	}

	@Override
	@Transactional
	public void update(Role role) {
		entityManager.merge(role);
	}

}

package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public boolean isExist(String username) {
        try {
            return entityManager.createQuery("SELECT c FROM User c WHERE c.userName = '" + username + "'", User.class).getSingleResult() != null;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    @Transactional
    public User findUserByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT c FROM User c WHERE c.userName = '" + username + "'", User.class).getSingleResult();
        } catch (Exception ex) {
            return null;
        }
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
    public User update(User user) {
        return entityManager.merge(user);
    }

}

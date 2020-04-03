package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.atcud.icecreamapp.entities.Recipe;
import com.atcud.icecreamapp.repositories.user.UserRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.User;
import com.atcud.icecreamapp.repositories.user.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    @Lazy
    UserRepositoryJpa userRepositoryJpa;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Page<User> findPage(Pageable pageable) {
        return userRepositoryJpa.findAll(pageable);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
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

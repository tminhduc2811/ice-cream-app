package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.repositories.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Customer> findAll() {
        return entityManager.createQuery("FROM Customer", Customer.class).getResultList();
    }

    @Override
    @Transactional
    public Optional<Customer> findById(Long id) {
        return Optional.of(entityManager.find(Customer.class, id));
    }

    @Override
    @Transactional
    public Customer findCustomerByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT c FROM Customer c WHERE c.userName = '" + username + "'", Customer.class).getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    @Override
    @Transactional
    public void delete(Customer customer) {
        entityManager.remove(customer);
    }

    @Override
    @Transactional
    public void update(Customer customer) {
        entityManager.merge(customer);
    }
}

package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Payment;
import com.atcud.icecreamapp.repositories.PaymentRepository;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Payment> findAll() {
        return entityManager.createQuery("FROM Payment", Payment.class).getResultList();
    }

    @Override
    @Transactional
    public Optional<Payment> findById(Long id) {
        return Optional.of(entityManager.find(Payment.class, id));
    }

    @Override
    @Transactional
    public Payment save(Payment payment) {
        entityManager.persist(payment);
        return payment;
    }

    @Override
    @Transactional
    public void delete(Payment payment) {
        entityManager.remove(payment);
    }

    @Override
    @Transactional
    public void update(Payment payment) {
        entityManager.merge(payment);
    }

}

package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.atcud.icecreamapp.DTO.entities.OrderDTO;
import com.atcud.icecreamapp.repositories.order.OrderRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Order;
import com.atcud.icecreamapp.repositories.order.OrderRepository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    @Lazy
    OrderRepositoryJpa orderRepositoryJpa;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Page<Order> findPage(Pageable pageable) {
        return orderRepositoryJpa.findAll(pageable);
    }

    @Override
    @Transactional
    public List<Order> findAll() {
        return entityManager.createQuery("FROM Order", Order.class).getResultList();
    }

    @Override
    @Transactional
    public Optional<Order> findById(Long id) {
        return Optional.of(entityManager.find(Order.class, id));
    }

    @Override
    @Transactional
    public Order save(Order onlineOrder) {
        entityManager.persist(onlineOrder);
        return onlineOrder;
    }

    @Override
    @Transactional
    public void delete(Order onlineOrder) {
        entityManager.remove(onlineOrder);

    }

    @Override
    @Transactional
    public void update(Order onlineOrder) {
        entityManager.merge(onlineOrder);

    }

}

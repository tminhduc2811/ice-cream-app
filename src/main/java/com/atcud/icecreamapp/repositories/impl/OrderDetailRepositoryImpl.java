package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.OrderDetail;
import com.atcud.icecreamapp.repositories.OrderDetailRepository;

@Repository
public class OrderDetailRepositoryImpl implements OrderDetailRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<OrderDetail> findAll() {
        return entityManager.createQuery("FROM OrderDetail", OrderDetail.class).getResultList();
    }

    @Override
    @Transactional
    public Optional<OrderDetail> findById(Long id) {
        return Optional.of(entityManager.find(OrderDetail.class, id));
    }

    @Override
    @Transactional
    public OrderDetail save(OrderDetail orderDetail) {
        entityManager.persist(orderDetail);
        return orderDetail;
    }

    @Override
    @Transactional
    public void delete(OrderDetail orderDetail) {
        entityManager.remove(orderDetail);
    }

    @Override
    @Transactional
    public void update(OrderDetail orderDetail) {
        entityManager.merge(orderDetail);
    }

}

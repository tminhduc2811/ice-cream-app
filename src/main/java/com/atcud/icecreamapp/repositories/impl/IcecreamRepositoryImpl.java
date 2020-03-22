package com.atcud.icecreamapp.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.atcud.icecreamapp.entities.User;
import org.springframework.stereotype.Repository;

import com.atcud.icecreamapp.entities.Icecream;
import com.atcud.icecreamapp.repositories.IcecreamRepository;

@Repository
public class IcecreamRepositoryImpl implements IcecreamRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Icecream> findAll() {
        return entityManager.createQuery("FROM Icecream", Icecream.class).getResultList();
    }

    @Override
    public Optional<Icecream> findById(Long id) {
        return Optional.of(entityManager.find(Icecream.class, id));
    }

    @Override
    public Icecream save(Icecream icecream) {
        entityManager.persist(icecream);
        return icecream;
    }

    @Override
    public void delete(Icecream icecream) {
        entityManager.remove(icecream);
    }

    @Override
    public void update(Icecream icecream) {
        entityManager.merge(icecream);
    }

    @Override
    public boolean isExisted(String icecreamName) {
        try {
            return entityManager.createQuery("SELECT c FROM Icecream c WHERE c.name = '" + icecreamName + "'", Icecream.class).getSingleResult() != null;
        } catch (Exception ex) {
            return false;
        }
    }

}

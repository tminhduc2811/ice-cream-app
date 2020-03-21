package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Customer;

public interface CustomerRepository {

    public List<Customer> findAll();

    public Optional<Customer> findById(Long id);

    public Customer save(Customer customer);

    public void delete(Customer customer);

    public void update(Customer customer);

}

package com.atcud.icecreamapp.repositories.customer;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerRepository {

    Page<Customer> findPage(Pageable pageable);

    public List<Customer> findAll();

    public Optional<Customer> findById(Long id);

    public Customer findCustomerByUsername(String username);

    public Customer save(Customer customer);

    public void delete(Customer customer);

    public Customer update(Customer customer);

    public boolean isExisted(String username);
}

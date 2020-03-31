package com.atcud.icecreamapp.repositories.customer;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerRepository {

    Page<Customer> findPage(Pageable pageable);

    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    Customer findCustomerByUsername(String username);

    Customer save(Customer customer);

    void delete(Customer customer);

    Customer update(Customer customer);

    boolean isExisted(String username);
}

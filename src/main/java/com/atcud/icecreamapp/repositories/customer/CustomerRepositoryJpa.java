package com.atcud.icecreamapp.repositories.customer;

import com.atcud.icecreamapp.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepositoryJpa extends JpaRepository<Customer, Long> {
}

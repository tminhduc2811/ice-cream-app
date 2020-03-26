package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.CustomerDTO;
import com.atcud.icecreamapp.entities.Customer;

public interface CustomerService {

    public List<CustomerDTO> getAllCustomers();

    public Optional<Customer> getCustomerById(Long id);

    public String login(String username, String password);

    public Customer register(Customer customer);

    public void delete(Long id);

    public void update(Customer customer);

}

package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.CustomerDTO;
import com.atcud.icecreamapp.DTO.entities.CustomerUpdateDTO;
import com.atcud.icecreamapp.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    public Page<CustomerDTO> findPage(Pageable pageable);

    public List<CustomerDTO> getAllCustomers();

    public Optional<Customer> getCustomerById(Long id);

    public Customer findUserByName(String username);

    public String login(String username, String password);

    public Customer register(Customer customer);

    public void delete(Long id);

    public Customer update(CustomerUpdateDTO customer);

}

package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.CustomerUpdateDTO;
import com.atcud.icecreamapp.DTO.entities.UserCredentials;
import com.atcud.icecreamapp.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    public Page<com.atcud.icecreamapp.DTO.entities.CustomerDTO> findPage(Pageable pageable);

    public List<com.atcud.icecreamapp.DTO.entities.CustomerDTO> getAllCustomers();

    public Optional<Customer> getCustomerById(Long id);

    public com.atcud.icecreamapp.DTO.entities.CustomerDTO findUserByName(String username);

    public String login(String username, String password);

    public com.atcud.icecreamapp.DTO.entities.CustomerDTO register(UserCredentials credentials);

    public void delete(Long id);

    public com.atcud.icecreamapp.DTO.entities.CustomerDTO update(CustomerUpdateDTO customer);

}

package com.atcud.icecreamapp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.exceptions.CustomException;
import com.atcud.icecreamapp.security.CustomerDetails;
import com.atcud.icecreamapp.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.DTO.entities.CustomerDTO;
import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.repositories.CustomerRepository;
import com.atcud.icecreamapp.services.CustomerService;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> entities = customerRepository.findAll();
        List<CustomerDTO> customers = new ArrayList<>();
        for (Customer customer : entities) {
            customers.add(DTOBuilder.customerToDTO(customer));
        }
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public String login(String username, String password) {
        try {
            Authentication authentication =
                    authenticationManager
                            .authenticate(
                                    new UsernamePasswordAuthenticationToken(
                                            username,
                                            password));
            // Inject current customer into security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtTokenProvider.generateTokenForCustomer((CustomerDetails) authentication.getPrincipal());
        } catch (AuthenticationException ex) {
            throw new CustomException("Invalid username or password", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public Customer register(Customer customer) {

        if (customerRepository.isExisted(customer.getUserName())) {
            throw new CustomException("Customer already existed", HttpStatus.CONFLICT);
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()) {
            throw new CustomException("Customer not found", HttpStatus.NOT_FOUND);
        }
        customerRepository.delete(optionalCustomer.get());
    }

    @Override
    public void update(Customer customer) {
        if (!customerRepository.isExisted(customer.getUserName())) {
            throw new CustomException("Customer not existed", HttpStatus.NOT_FOUND);
        }
        customerRepository.update(customer);
    }

}

package com.atcud.icecreamapp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.CustomerUpdateDTO;
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
    public Customer findUserByName(String username) {
        Customer customer = customerRepository.findCustomerByUsername(username);
        if (username == null) {
            throw new CustomException("Customer not found", HttpStatus.NOT_FOUND);
        }
        return customer;
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
    public Customer update(CustomerUpdateDTO customer) {
        Customer currentCustomer = customerRepository.findCustomerByUsername(customer.getCustomer().getUserName());
        if (currentCustomer == null) {
            throw new CustomException("Customer " + customer.getCustomer().getUserName() + " not found", HttpStatus.NOT_FOUND);
        }
        String currentPass = customer.getCurrentPassword();
        if (!currentPass.equals("")) {
            if(passwordEncoder.matches(currentPass, currentCustomer.getPassword())) {
                currentCustomer.setPassword(passwordEncoder.encode(customer.getCustomer().getPassword()));
            } else {
                throw new CustomException("Invalid password", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
        currentCustomer.setFirstName(customer.getCustomer().getFirstName());
        currentCustomer.setLastName(customer.getCustomer().getLastName());
        currentCustomer.setBirthday(customer.getCustomer().getBirthday());
        currentCustomer.setGender(customer.getCustomer().getGender());
        currentCustomer.setAddress(customer.getCustomer().getAddress());
        currentCustomer.setPhoneNumber(customer.getCustomer().getPhoneNumber());
        currentCustomer.setEmail(customer.getCustomer().getEmail());
        currentCustomer.setAvatar(customer.getCustomer().getAvatar());
        return customerRepository.update(currentCustomer);
    }

}

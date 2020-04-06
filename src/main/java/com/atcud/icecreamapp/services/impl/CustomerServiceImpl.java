package com.atcud.icecreamapp.services.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.CustomerUpdateDTO;
import com.atcud.icecreamapp.DTO.entities.UserCredentials;
import com.atcud.icecreamapp.exceptions.CustomException;
import com.atcud.icecreamapp.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.DTO.entities.CustomerDTO;
import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.repositories.customer.CustomerRepository;
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
    public Page<CustomerDTO> findPage(Pageable pageable) {
        Page<Customer> entityPage = customerRepository.findPage(pageable);
        return DTOBuilder.mapPage(entityPage, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> entities = customerRepository.findAll();
        return DTOBuilder.mapList(entities, CustomerDTO.class);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public CustomerDTO findUserByName(String username) {
        Customer customer = customerRepository.findCustomerByUsername(username);
        if (customer == null) {
            throw new CustomException("Customer not found", HttpStatus.NOT_FOUND);
        }
        return DTOBuilder.mapObject(customer, CustomerDTO.class);
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
            return jwtTokenProvider.generateToken(authentication);
        } catch (AuthenticationException ex) {
            throw new CustomException("Invalid username or password", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public CustomerDTO register(UserCredentials credentials) {

        if (customerRepository.isExisted(credentials.getUsername())) {
            throw new CustomException("Customer already existed", HttpStatus.CONFLICT);
        }
        Customer customer = new Customer();
        customer.setUserName(credentials.getUsername());
        customer.setEmail(credentials.getEmail());
        customer.setPassword(passwordEncoder.encode(credentials.getPassword()));
        LocalDate ld = LocalDate.now();
        ld = ld.plusYears(2);
        Date expiredDate = Date.valueOf(ld);
        customer.setExpiredDate(expiredDate);
        customer.setStatus((short) 1);
        customer.setNumOfLoginFailed((short) 0);
        return DTOBuilder.mapObject(customerRepository.save(customer), CustomerDTO.class);
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
    public CustomerDTO update(CustomerUpdateDTO customer) {
        Customer currentCustomer = customerRepository.findCustomerByUsername(customer.getCustomer().getUserName());
        if (currentCustomer == null) {
            throw new CustomException("Customer " + customer.getCustomer().getUserName() + " not found", HttpStatus.NOT_FOUND);
        }
        String currentPass = customer.getCurrentPassword();
        if (!currentPass.equals("")) {
            if (passwordEncoder.matches(currentPass, currentCustomer.getPassword())) {
                currentCustomer.setPassword(passwordEncoder.encode(customer.getNewPassword()));
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
        currentCustomer.setStatus(customer.getCustomer().getStatus());
        return DTOBuilder.mapObject(customerRepository.update(currentCustomer), CustomerDTO.class);
    }

}

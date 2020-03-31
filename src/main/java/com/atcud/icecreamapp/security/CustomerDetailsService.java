package com.atcud.icecreamapp.security;

import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.exceptions.CustomException;
import com.atcud.icecreamapp.repositories.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }

        if (customer.getStatus() != 1) {
            throw new CustomException("Customer has been disabled", HttpStatus.UNAUTHORIZED);
        }

        return new CustomerDetails(customer);
    }
}

package com.atcud.icecreamapp.controllers;

import java.util.List;
import com.atcud.icecreamapp.DTO.entities.LoginResponseDTO;
import com.atcud.icecreamapp.DTO.entities.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atcud.icecreamapp.DTO.entities.CustomerDTO;
import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        List<CustomerDTO> customers = service.getAllCustomers();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        Customer result = service.register(customer);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
//    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
//        ;
//        return new ResponseEntity<>(service.update(customer), HttpStatus.OK);
//    }

    // TODO: Refactor later
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLogin userLogin) {
        String token = service.login(userLogin.getUsername(), userLogin.getPassword());
        return new ResponseEntity<>(new LoginResponseDTO(userLogin.getUsername(), token), HttpStatus.OK);
    }
}

package com.atcud.icecreamapp.controllers;

import com.atcud.icecreamapp.DTO.entities.LoginResponseDTO;
import com.atcud.icecreamapp.DTO.entities.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<com.atcud.icecreamapp.DTO.entities.CustomerDTO>> getCustomers(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        } else if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        assert sortable != null;
        Pageable pageable = PageRequest.of(page, size, sortable);
        return new ResponseEntity<>(customerService.findPage(pageable), HttpStatus.OK);
    }
//
//    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
//    public ResponseEntity<CustomerDTO> registerCustomer(@RequestBody Customer customer) {
//        return new ResponseEntity<>(customerService.register(customer), HttpStatus.CREATED);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
//    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
//        ;
//        return new ResponseEntity<>(service.update(customer), HttpStatus.OK);
//    }

    // TODO: Refactor later
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserCredentials userLogin) {
        String token = customerService.login(userLogin.getUsername(), userLogin.getPassword());
        return new ResponseEntity<>(new LoginResponseDTO(userLogin.getUsername(), token), HttpStatus.OK);
    }
}

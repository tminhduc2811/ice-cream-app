package com.atcud.icecreamapp.controllers;

import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.DTO.entities.CustomerDTO;
import com.atcud.icecreamapp.DTO.entities.CustomerUpdateDTO;
import com.atcud.icecreamapp.DTO.entities.UserDTO;
import com.atcud.icecreamapp.DTO.entities.UserUpdateDTO;
import com.atcud.icecreamapp.services.CustomerService;
import com.atcud.icecreamapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String username) {
        UserDTO userDTO = userService.findUserByUsername(username);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        UserDTO updatedUser = userService.update(userUpdateDTO);
        System.out.println("Update user successfully");
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{username}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable String username) {
        return new ResponseEntity<>(customerService.findUserByName(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        System.out.println("Update customer successfully");
        return new ResponseEntity<>(customerService.update(customerUpdateDTO), HttpStatus.OK);
    }
}

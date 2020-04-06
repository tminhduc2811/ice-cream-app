package com.atcud.icecreamapp.controllers;

import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.DTO.entities.CustomerDTO;
import com.atcud.icecreamapp.DTO.entities.LoginResponseDTO;
import com.atcud.icecreamapp.DTO.entities.UserCredentials;
import com.atcud.icecreamapp.services.CustomerService;
import com.atcud.icecreamapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    // TODO: Refactor later
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserCredentials userLogin) {
        // Check user/admin
        String token = "";
        try {
            return new ResponseEntity<>(DTOBuilder.loginResponseDTO(new LoginResponseDTO(userLogin.getUsername(),
                    userService.login(userLogin.getUsername(), userLogin.getPassword()))), HttpStatus.OK);
        } catch (Exception ex) {
            token = customerService.login(userLogin.getUsername(), userLogin.getPassword());
        }
        return new ResponseEntity<>(DTOBuilder.loginResponseDTO(
                new LoginResponseDTO(
                        userLogin.getUsername(), token)), HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<CustomerDTO> register(@RequestBody UserCredentials userCredentials) {
        return new ResponseEntity<>(customerService.register(userCredentials), HttpStatus.CREATED);
    }
}

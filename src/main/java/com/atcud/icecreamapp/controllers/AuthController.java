package com.atcud.icecreamapp.controllers;

import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.DTO.entities.LoginResponseDTO;
import com.atcud.icecreamapp.DTO.entities.UserLogin;
import com.atcud.icecreamapp.services.CustomerService;
import com.atcud.icecreamapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLogin userLogin) {
        // Check user/admin
        String token = null;
        try {
            token = userService.login(userLogin.getUsername(), userLogin.getPassword());
        } catch (Exception ex) {
            if (ex.getMessage().equals("Invalid username or password")) {
                token = customerService.login(userLogin.getUsername(), userLogin.getPassword());
            }
        }
        return new ResponseEntity<>(DTOBuilder.loginResponseDTO(
                new LoginResponseDTO(
                        userLogin.getUsername(), token)), HttpStatus.OK);
    }
}

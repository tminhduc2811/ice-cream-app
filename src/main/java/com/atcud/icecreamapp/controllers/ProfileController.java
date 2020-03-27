package com.atcud.icecreamapp.controllers;

import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.DTO.entities.UserDTO;
import com.atcud.icecreamapp.entities.User;
import com.atcud.icecreamapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String username) {
        User user = service.findUserByUsername(username);
        return new ResponseEntity<>(DTOBuilder.userToDTO(user), HttpStatus.OK);
    }

}

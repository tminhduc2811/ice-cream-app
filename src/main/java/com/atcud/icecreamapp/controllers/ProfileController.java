package com.atcud.icecreamapp.controllers;

import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.DTO.entities.UserDTO;
import com.atcud.icecreamapp.DTO.entities.UserUpdateDTO;
import com.atcud.icecreamapp.entities.User;
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
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String username) {
        User user = service.findUserByUsername(username);
        return new ResponseEntity<>(DTOBuilder.userToDTO(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        User updatedUser = service.update(userUpdateDTO);
        System.out.println("Update user successfully");
        return new ResponseEntity<>(DTOBuilder.userToDTO(updatedUser), HttpStatus.OK);
    }
}

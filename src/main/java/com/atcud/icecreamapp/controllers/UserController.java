package com.atcud.icecreamapp.controllers;

import com.atcud.icecreamapp.DTO.LoginResponseDTO;
import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.entities.FAQ;
import com.atcud.icecreamapp.entities.User;
import com.atcud.icecreamapp.entities.UserLogin;
import com.atcud.icecreamapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = service.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = service.findUserByUsername(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLogin userLogin) {
        String token = service.login(userLogin.getUserName(), userLogin.getPassword());

        return new ResponseEntity<LoginResponseDTO>(new LoginResponseDTO(userLogin.getUserName(), token), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<FAQ> deleteUser(@PathVariable Long id) {
        Optional<User> user = service.getUserById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(user.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // TODO: modify this controller later
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> createCustomer(@RequestBody User user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        User result = service.save(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}

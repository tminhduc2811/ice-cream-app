package com.atcud.icecreamapp.controllers;

import com.atcud.icecreamapp.DTO.LoginResponseDTO;
import com.atcud.icecreamapp.DTO.entities.RoleIdList;
import com.atcud.icecreamapp.DTO.entities.UserLogin;
import com.atcud.icecreamapp.entities.*;
import com.atcud.icecreamapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLogin userLogin) {
        String token = service.login(userLogin.getUserName(), userLogin.getPassword());

        return new ResponseEntity<>(new LoginResponseDTO(userLogin.getUserName(), token), HttpStatus.OK);
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
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> createCustomer(@RequestBody User user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        User result = service.save(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/roles", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Role>> getUserRoles(@PathVariable Long id) {
        return new ResponseEntity<>(service.getUserRoles(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/roles", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> modifyUserRoles(@PathVariable Long id, @RequestBody Map<String, List<Long>> roleIds) {
        service.updateUserRoles(id, roleIds.get("roleIds"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

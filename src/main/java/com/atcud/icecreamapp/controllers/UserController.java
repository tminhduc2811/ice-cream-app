package com.atcud.icecreamapp.controllers;

import com.atcud.icecreamapp.entities.FAQ;
import com.atcud.icecreamapp.entities.User;
import com.atcud.icecreamapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

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
    @RequestMapping(value="", method=RequestMethod.POST, produces="application/json" )
    public ResponseEntity<FAQ> createFAQ(@RequestBody FAQ newFAQ) {
        FAQ faq = service.save(newFAQ);
        return new ResponseEntity<>(faq, HttpStatus.CREATED);
    }

}

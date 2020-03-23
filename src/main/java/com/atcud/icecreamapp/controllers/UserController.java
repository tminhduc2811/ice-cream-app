package com.atcud.icecreamapp.controllers;

import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.DTO.LoginResponseDTO;
import com.atcud.icecreamapp.DTO.UserDTO;
import com.atcud.icecreamapp.DTO.entities.UserLogin;
import com.atcud.icecreamapp.entities.*;
import com.atcud.icecreamapp.services.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(value = "User APIs")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @ApiOperation(value = "Get information of all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Getting users successfully"),
            @ApiResponse(code = 204, message = "There is no user in the DB"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Access denied, user is not allowed to access this resource"),
            @ApiResponse(code = 500, message = "Internal server error, there was an exception")
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = service.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation(value = "Get user with User's Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Getting user successfully"),
            @ApiResponse(code = 204, message = "User not found"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Access denied, user is not allowed to access this resource"),
            @ApiResponse(code = 500, message = "Internal server error, there was an exception")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = service.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Login for user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Logged in successfully"),
            @ApiResponse(code = 422, message = "Invalid username or password"),
            @ApiResponse(code = 500, message = "Internal server error, there was an exception")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLogin userLogin) {
        String token = service.login(userLogin.getUserName(), userLogin.getPassword());

        return new ResponseEntity<>(new LoginResponseDTO(userLogin.getUserName(), token), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete user by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error, there was an exception"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 204, message = "User deleted successfully")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<FAQ> deleteUser(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Register new user successfully"),
            @ApiResponse(code = 409, message = "User already existed"),
            @ApiResponse(code = 500, message = "Internal server error, there was an exception")
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user) {
        User result = service.register(user);
        return new ResponseEntity<>(DTOBuilder.userToDTO(result), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all roles of user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Getting user roles successfully"),
            @ApiResponse(code = 404, message = "User's Id not found"),
            @ApiResponse(code = 500, message = "Internal server error, there was an exception")
    })
    @RequestMapping(value = "/{id}/roles", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Role>> getUserRoles(@PathVariable Long id) {
        return new ResponseEntity<>(service.getUserRoles(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Modify user's roles")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Modified user roles successfully"),
            @ApiResponse(code = 404, message = "User's Id or Role not found"),
            @ApiResponse(code = 500, message = "Internal server error, there was an exception")
    })
    @RequestMapping(value = "/{id}/roles", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> modifyUserRoles(@PathVariable Long id, @RequestBody Map<String, List<Long>> roleIds) {
        service.updateUserRoles(id, roleIds.get("roleIds"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

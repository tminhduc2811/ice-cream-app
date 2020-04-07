package com.atcud.icecreamapp.controllers;

import com.atcud.icecreamapp.DTO.entities.*;
import com.atcud.icecreamapp.entities.*;
import com.atcud.icecreamapp.services.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Api(value = "User APIs")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "Get information of all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Getting users successfully"),
            @ApiResponse(code = 204, message = "There is no user in the DB"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Access denied, user is not allowed to access this resource"),
            @ApiResponse(code = 500, message = "Internal server error, there was an exception")
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<UserDTO>> getUsers(
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
        return new ResponseEntity<>(userService.findPage(pageable), HttpStatus.OK);
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
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

//    @ApiOperation(value = "Login for user")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Logged in successfully"),
//            @ApiResponse(code = 422, message = "Invalid username or password"),
//            @ApiResponse(code = 500, message = "Internal server error, there was an exception")
//    })
//    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
//    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserLogin userLogin) {
//        String token = service.login(userLogin.getUsername(), userLogin.getPassword());
//        UserDTO userDTO = service.findUserByUsername(userLogin.getUsername());
//        return new ResponseEntity<>(DTOBuilder.authResponseToDTO(DTOBuilder.authInfoToDTO(user), token), HttpStatus.OK);
//    }

    @ApiOperation(value = "Delete user by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error, there was an exception"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 204, message = "User deleted successfully")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<FAQ> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Register new user successfully"),
            @ApiResponse(code = 409, message = "User already existed"),
            @ApiResponse(code = 500, message = "Internal server error, there was an exception")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user) {
        UserDTO result = userService.register(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all roles of user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Getting user roles successfully"),
            @ApiResponse(code = 404, message = "User's Id not found"),
            @ApiResponse(code = 500, message = "Internal server error, there was an exception")
    })
    @RequestMapping(value = "/{id}/roles", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Role>> getUserRoles(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserRoles(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Modify user's roles")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Modified user roles successfully"),
            @ApiResponse(code = 404, message = "User's Id or Role not found"),
            @ApiResponse(code = 500, message = "Internal server error, there was an exception")
    })
    @RequestMapping(value = "/{id}/roles", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> modifyUserRoles(@PathVariable Long id, @RequestBody Map<String, List<Long>> roleIds) {
        userService.updateUserRoles(id, roleIds.get("roleIds"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.atcud.icecreamapp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Role;
import com.atcud.icecreamapp.exceptions.CustomException;
import com.atcud.icecreamapp.repositories.RoleRepository;
import com.atcud.icecreamapp.security.CustomUserDetails;
import com.atcud.icecreamapp.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.User;
import com.atcud.icecreamapp.repositories.UserRepository;
import com.atcud.icecreamapp.services.UserService;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public String login(String username, String password) {
        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            // Inject current user into security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        } catch (AuthenticationException ex) {
            throw new CustomException("Invalid username or password", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public List<Role> getUserRoles(Long id) {

        return userRepository.findById(id).get().getRoles();
    }

    @Override
    public void updateUserRoles(Long id, List<Long> roleIds) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new CustomException("User with Id: " + "not found", HttpStatus.NOT_FOUND);
        }
        User user = optional.get();
        List<Role> roles = new ArrayList<>();
        for (Long i : roleIds) {
            Optional<Role> temp = roleRepository.findById(i);
            if (!temp.isPresent()) {
                throw new CustomException("Role not exist", HttpStatus.NOT_FOUND);

            }
            roles.add(temp.get());
        }
        user.modifyRoles(roles);
        userRepository.save(user);
    }

}

package com.atcud.icecreamapp.security;

import com.atcud.icecreamapp.entities.User;
import com.atcud.icecreamapp.exceptions.CustomException;
import com.atcud.icecreamapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new CustomException("Invalid user name or password", HttpStatus.NOT_FOUND);
        }
        return new CustomUserDetails(user);
    }
}

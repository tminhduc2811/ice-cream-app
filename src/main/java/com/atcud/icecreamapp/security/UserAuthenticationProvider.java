package com.atcud.icecreamapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomUserService customUserService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            UserDetails user = customUserService.loadUserByUsername(authentication.getName());

            UsernamePasswordAuthenticationToken result = null;
            // Check user
            if(user != null) {
                if(user.getUsername().equals(authentication.getName()) && passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
                    result = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());

                }
            } else {
                throw new BadCredentialsException("User authentication failed");
            }
            return result;
        } catch (Exception ex) {
            throw new BadCredentialsException("User authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

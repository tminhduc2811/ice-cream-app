package com.atcud.icecreamapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomUserService customUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            UserDetails user = customUserService.loadUserByUsername(authentication.getName());

            // Check user
            UsernamePasswordAuthenticationToken result = null;
            if(user.getUsername().equals(authentication.getName()) && user.getPassword().equals(authentication.getCredentials().toString())) {
                result = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());

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

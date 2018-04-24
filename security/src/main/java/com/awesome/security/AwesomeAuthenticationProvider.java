package com.awesome.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AwesomeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AwesomeUserService awesomeUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        AwesomeUserDetails userDetails = (AwesomeUserDetails) awesomeUserService.loadUserByUsername(authentication.getName());
        //根据实际情况选择合适加密算法
        if (!userDetails.getPassword().equals(token.getCredentials().toString())) {
            throw new BadCredentialsException("password error.");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

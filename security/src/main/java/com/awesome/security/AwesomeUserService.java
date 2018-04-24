package com.awesome.security;

import com.awesome.model.User;
import com.awesome.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AwesomeUserService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByName(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("invalid username.");
        }
        List<String> authorities = userService.findUserAuthoritiesByName(username);
        return new AwesomeUserDetails(authorities, user);
    }
}

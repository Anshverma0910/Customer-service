package com.customer.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("anshverma".equals(username)) {
            return new User("anshverma", "$2a$08$udGnPGnrQTy27NfUoqizU.7UwOHe.vx55wIExBwGWwnJ0lojoKAUm",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException(username + " is not found in the records");
        }
    }
}
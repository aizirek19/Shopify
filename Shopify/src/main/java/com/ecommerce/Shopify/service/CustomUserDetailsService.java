package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.entities.User;
import com.ecommerce.Shopify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ecommerce.Shopify.entities.CustomUserDetails;


import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository customUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> customUser = customUserRepository.getByEmail(username);
        return customUser.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));

    }

}
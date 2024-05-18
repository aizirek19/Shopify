package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.UserRequest;
import com.ecommerce.Shopify.entities.*;
import com.ecommerce.Shopify.exceptions.UserNotFoundException;
import com.ecommerce.Shopify.jwt.JwtHelper;
import com.ecommerce.Shopify.repositories.RoleRepository;
import com.ecommerce.Shopify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository userRolesRepository;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Object> signUp(UserRequest userRequest) {
        User user = new User();
        if (userRequest.getEmail() == null || userRequest.getEmail().length() == 0) {
            return new ResponseEntity<>(new MyExceptionDetails("Empty Email field !", "uri=/auth/signUp"), HttpStatus.BAD_REQUEST);
        }
        if (userRequest.getPassword() == null || userRequest.getPassword().length() == 0) {
            return new ResponseEntity<>(new MyExceptionDetails("Empty password field !", "uri=/auth/signUp"), HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getEmail());
        Optional<User> optionalUser = userRepository.getByEmail(userRequest.getEmail());

        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(new MyExceptionDetails("User already registered !", HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
        }

        // validate Role
        Role newRole = new Role();

        Optional<Role> optionalRole = userRolesRepository.findByRoleName("ROLE_ADMIN");
        if (optionalRole.isEmpty()) {
            return new ResponseEntity<>(new MyExceptionDetails("User Role 'ROLE_USER' Not Available !", HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
        }

        newRole = optionalRole.get();
        user.setRole(newRole);


        userRepository.save(user);


        String token = helper.generateToken(userRequest.getEmail());

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setJwtToken(token);
        jwtResponse.setUserName(userRequest.getEmail());
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

    }

    public ResponseEntity<Object> signIn(JwtRequest jwtRequest) {

        String email = jwtRequest.getEmail();
        String password = jwtRequest.getPassword();

        if (email == null || email.length() == 0) {
            return new ResponseEntity<>(new MyExceptionDetails("Empty Email field !", "uri=/auth/signUp"), HttpStatus.BAD_REQUEST);
        }
        if (password == null || password.length() == 0) {
            return new ResponseEntity<>(new MyExceptionDetails("Empty password field !", "uri=/auth/signUp"), HttpStatus.BAD_REQUEST);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        try {
            authenticationProvider.authenticate(usernamePasswordAuthenticationToken);

        } catch (Exception e) {
            throw new UserNotFoundException("Invalid Username or Password  ! " + e);
        }

        String token = helper.generateToken(email);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setJwtToken(token);
        jwtResponse.setUserName(email);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

    }

}

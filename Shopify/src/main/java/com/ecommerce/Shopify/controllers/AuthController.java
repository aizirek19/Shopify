package com.ecommerce.Shopify.controllers;

import com.ecommerce.Shopify.dto.UserRequest;
import com.ecommerce.Shopify.entities.JwtRequest;
import com.ecommerce.Shopify.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody UserRequest customUser) {

        return authService.signUp(customUser);
    }

    @PostMapping("/signIn")
    public ResponseEntity<Object> signIn(@RequestBody JwtRequest jwtRequest) {

        return authService.signIn(jwtRequest);

    }

}
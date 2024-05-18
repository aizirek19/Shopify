package com.ecommerce.Shopify.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

//    public ResponseEntity<User> getUser(String email) {
//        Optional<User> result = customUserRepository.findByEmail(email);
//        User user = new User();
//        if(result.isPresent()) {
//            user = result.get();
//        }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    //    public ResponseEntity<List<User>> getUsers() {
//        List<User> users = customUserRepository.findAll();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
    void deleteUser(Long userId);
}
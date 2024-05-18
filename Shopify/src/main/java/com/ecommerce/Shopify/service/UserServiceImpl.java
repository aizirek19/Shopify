package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.entities.User;
//import com.ecommerce.Shopify.mappers.UserMapper;
import com.ecommerce.Shopify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository/*, UserMapper userMapper*/) {
        this.userRepository = userRepository;
//        this.userMapper = userMapper;
    }

//    @Override
//    public UserDTO getUserById(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
//        return userMapper.userToUserDTO(user);
//    }

    public ResponseEntity<User> getUserByEmail(String email) {
        Optional<User> result = userRepository.getByEmail(email);
        User user = new User();
        if (result.isPresent()) {
            user = result.get();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @Override
//    public List<UserDTO> getAllUsers() {
//        return null;
//    }
//
//    @Override
//    public void createUser(UserDTO userDTO) {
//
//    }
//
//    @Override
//    public void updateUser(Long userId, UserDTO userDTO) {
//
//    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null || email.isEmpty()) {
            throw new UsernameNotFoundException("username is empty");
        }
        return userRepository.getByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + "is not found"));
    }
}

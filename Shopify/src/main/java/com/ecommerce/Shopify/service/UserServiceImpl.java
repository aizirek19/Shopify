package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.UserDTO;
import com.ecommerce.Shopify.entities.User;
import com.ecommerce.Shopify.mappers.UserMapper;
import com.ecommerce.Shopify.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return userMapper.userToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public void createUser(UserDTO userDTO) {

    }

    @Override
    public void updateUser(Long userId, UserDTO userDTO) {

    }

    @Override
    public void deleteUser(Long userId) {

    }

}

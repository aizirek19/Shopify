package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserById(Long userId);
    List<UserDTO> getAllUsers();
    void createUser(UserDTO userDTO);
    void updateUser(Long userId, UserDTO userDTO);
    void deleteUser(Long userId);
}

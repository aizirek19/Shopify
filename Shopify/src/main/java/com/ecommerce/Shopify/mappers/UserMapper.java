//package com.ecommerce.Shopify.mappers;
//
//import com.ecommerce.Shopify.dto.UserDTO;
//import com.ecommerce.Shopify.entities.User;
//import org.mapstruct.Mapper;
//
//@Mapper
//public interface UserMapper {
//
//    static UserDTO toDTO(User user) {
//        if (user == null) {
//            return null;
//        }
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getUserId());
//        userDTO.setUsername(user.getUsername());
//        userDTO.setEmail(user.getEmail());
//        // Map other fields as needed
//        return userDTO;
//    }
//
//    static User toEntity(UserDTO userDTO) {
//        if (userDTO == null) {
//            return null;
//        }
//
//        User user = new User();
//        user.setUserId(userDTO.getId());
//        user.setEmail(userDTO.getUsername());
//        user.setEmail(userDTO.getEmail());
//        // Map other fields as needed
//        return user;
//    }
//
//    UserDTO userToUserDTO(User user);
//}
//
//

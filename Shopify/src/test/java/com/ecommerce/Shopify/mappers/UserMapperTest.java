package com.ecommerce.Shopify.mappers;

import com.ecommerce.Shopify.dto.UserDTO;
import com.ecommerce.Shopify.entities.User;
import com.ecommerce.Shopify.mappers.UserMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {

    @Test
    public void testToDTO() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("test_user");
        user.setEmail("test@example.com");

        // Act
        UserDTO userDTO = UserMapper.toDTO(user);

        // Assert
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getUsername(), userDTO.getUsername());
        assertEquals(user.getEmail(), userDTO.getEmail());
    }

    @Test
    public void testToEntity() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("test_user");
        userDTO.setEmail("test@example.com");

        // Act
        User user = UserMapper.toEntity(userDTO);

        // Assert
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getEmail(), user.getEmail());
    }
}
package com.ecommerce.Shopify.controller.integration;

import com.ecommerce.Shopify.controllers.UserController;
import com.ecommerce.Shopify.dto.UserDTO;
//import com.ecommerce.Shopify.services.UserService;
import com.ecommerce.Shopify.service.UserService;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test_user");
        userDTO.setEmail("test@example.com");

        GsonJsonProvider JsonUtils = new GsonJsonProvider();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(userDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetUserById() throws Exception {
        Long userId = 1L; // Assuming user with ID 1 exists

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

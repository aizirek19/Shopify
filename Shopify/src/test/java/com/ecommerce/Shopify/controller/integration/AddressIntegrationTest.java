package com.ecommerce.Shopify.controller.integration;

import com.ecommerce.Shopify.controllers.AddressController;
import com.ecommerce.Shopify.dto.AddressDTO;
//import com.ecommerce.Shopify.services.AddressService;
import com.ecommerce.Shopify.service.AddressService;
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
public class AddressIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressService addressService;

    @Test
    public void testCreateAddress() throws Exception {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet("123 Test Street");
        addressDTO.setCity("Test City");
        addressDTO.setCountry("Test Country");

        GsonJsonProvider JsonUtils = new GsonJsonProvider();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(addressDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    // Add integration tests for other CRUD operations and components
}


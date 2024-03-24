package com.ecommerce.Shopify.controllerTests;

import com.ecommerce.Shopify.controllers.AddressController;
import com.ecommerce.Shopify.dto.AddressDTO;
//import com.ecommerce.Shopify.services.AddressService;
import com.ecommerce.Shopify.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Test
    public void testGetAddressById() throws Exception {
        Long addressId = 1L;
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(addressId);
        addressDTO.setStreet("123 Main St");
        addressDTO.setCity("New York");
        addressDTO.setState("NY");
        addressDTO.setZipCode("10001");

        when(addressService.getAddressById(addressId)).thenReturn(addressDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/addresses/{addressId}", addressId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(addressId))
                .andExpect(jsonPath("$.street").value("123 Main St"))
                .andExpect(jsonPath("$.city").value("New York"))
                .andExpect(jsonPath("$.state").value("NY"))
                .andExpect(jsonPath("$.zipCode").value("10001"));
    }

    @Test
    public void testCreateAddress() throws Exception {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet("456 Elm St");
        addressDTO.setCity("Los Angeles");
        addressDTO.setState("CA");
        addressDTO.setZipCode("90001");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/addresses")
                        .content(asJsonString(addressDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(addressService, times(1)).createAddress(addressDTO);
    }

    @Test
    public void testUpdateAddress() throws Exception {
        Long addressId = 1L;
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet("Updated Street");
        addressDTO.setCity("Updated City");
        addressDTO.setState("Updated State");
        addressDTO.setZipCode("Updated ZipCode");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/addresses/{addressId}", addressId)
                        .content(asJsonString(addressDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(addressService, times(1)).updateAddress(addressId, addressDTO);
    }

    @Test
    public void testDeleteAddress() throws Exception {
        Long addressId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/addresses/{addressId}", addressId))
                .andExpect(status().isNoContent());

        verify(addressService, times(1)).deleteAddress(addressId);
    }

    // Вспомогательный метод для преобразования объекта в JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

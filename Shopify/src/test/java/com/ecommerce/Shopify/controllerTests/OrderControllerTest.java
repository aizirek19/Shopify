package com.ecommerce.Shopify.controllerTests;

import com.ecommerce.Shopify.controllers.OrderController;
import com.ecommerce.Shopify.dto.OrderDTO;
//import com.ecommerce.Shopify.services.OrderService;
import com.ecommerce.Shopify.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void testGetOrderById() throws Exception {
        Long orderId = 1L;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderId);
        orderDTO.setOrderDate(LocalDateTime.now());

        when(orderService.getOrderById(orderId)).thenReturn(orderDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderId))
                .andExpect(jsonPath("$.orderDate").exists());
    }

    @Test
    public void testCreateOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderDate(LocalDateTime.now());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                        .content(asJsonString(orderDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(orderService, times(1)).createOrder(orderDTO);
    }

    @Test
    public void testUpdateOrder() throws Exception {
        Long orderId = 1L;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderDate(LocalDateTime.now());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/orders/{orderId}", orderId)
                        .content(asJsonString(orderDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(orderService, times(1)).updateOrder(orderId, orderDTO);
    }

    @Test
    public void testDeleteOrder() throws Exception {
        Long orderId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/orders/{orderId}", orderId))
                .andExpect(status().isNoContent());

        verify(orderService, times(1)).deleteOrder(orderId);
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

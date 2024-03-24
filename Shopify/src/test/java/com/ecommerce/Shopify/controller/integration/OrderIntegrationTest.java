package com.ecommerce.Shopify.controller.integration;

import com.ecommerce.Shopify.controllers.OrderController;
import com.ecommerce.Shopify.dto.OrderDTO;
//import com.ecommerce.Shopify.services.OrderService;
import com.ecommerce.Shopify.service.OrderService;
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
public class OrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Test
    public void testCreateOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        // Set order details

        GsonJsonProvider JsonUtils = new GsonJsonProvider();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(orderDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    // Add integration tests for other CRUD operations and components
}

package com.ecommerce.Shopify.controller.integration;

import com.ecommerce.Shopify.controllers.PaymentController;
import com.ecommerce.Shopify.dto.PaymentDTO;
//import com.ecommerce.Shopify.services.PaymentService;
import com.ecommerce.Shopify.service.PaymentService;
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
public class PaymentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PaymentService paymentService;

    @Test
    public void testCreatePayment() throws Exception {
        PaymentDTO paymentDTO = new PaymentDTO();
        // Set payment details

        GsonJsonProvider JsonUtils = new GsonJsonProvider();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(paymentDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    // Add integration tests for other CRUD operations and components
}

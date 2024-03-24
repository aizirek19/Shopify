package com.ecommerce.Shopify.controllerTests;

import com.ecommerce.Shopify.controllers.PaymentController;
import com.ecommerce.Shopify.dto.PaymentDTO;
//import com.ecommerce.Shopify.services.PaymentService;
import com.ecommerce.Shopify.service.PaymentService;
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

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    public void testGetPaymentById() throws Exception {
        Long paymentId = 1L;
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(paymentId);
        paymentDTO.setPaymentMethod("Credit Card");
        paymentDTO.setAmount(100.00);

        when(paymentService.getPaymentById(paymentId)).thenReturn(paymentDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/payments/{paymentId}", paymentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(paymentId))
                .andExpect(jsonPath("$.paymentMethod").value("Credit Card"))
                .andExpect(jsonPath("$.amount").value(100.00));
    }

    @Test
    public void testCreatePayment() throws Exception {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentMethod("Credit Card");
        paymentDTO.setAmount(100.00);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/payments")
                        .content(asJsonString(paymentDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(paymentService, times(1)).createPayment(paymentDTO);
    }

    @Test
    public void testUpdatePayment() throws Exception {
        Long paymentId = 1L;
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentMethod("Updated Payment Method");
        paymentDTO.setAmount(200.00);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/payments/{paymentId}", paymentId)
                        .content(asJsonString(paymentDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(paymentService, times(1)).updatePayment(paymentId, paymentDTO);
    }

    @Test
    public void testDeletePayment() throws Exception {
        Long paymentId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/payments/{paymentId}", paymentId))
                .andExpect(status().isNoContent());

        verify(paymentService, times(1)).deletePayment(paymentId);
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

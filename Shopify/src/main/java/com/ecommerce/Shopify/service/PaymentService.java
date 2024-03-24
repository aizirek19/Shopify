package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO getPaymentById(Long paymentId);
    List<PaymentDTO> getAllPayments();
    void createPayment(PaymentDTO paymentDTO);
    void updatePayment(Long paymentId, PaymentDTO paymentDTO);
    void deletePayment(Long paymentId);
}

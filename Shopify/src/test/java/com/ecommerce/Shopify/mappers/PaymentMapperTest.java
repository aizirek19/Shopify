package com.ecommerce.Shopify.mappers;

import com.ecommerce.Shopify.dto.PaymentDTO;
import com.ecommerce.Shopify.entities.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentMapperTest {

    @Test
    public void testPaymentToPaymentDTO() {
        // Создаем объект Payment
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setPaymentMethod("Credit Card");
        payment.setAmount(69.98);
        payment.setOrderId(1L);

        // Преобразуем его в DTO
        PaymentDTO paymentDTO = PaymentMapper.INSTANCE.paymentToPaymentDTO(payment);

        // Проверяем, что поля были правильно скопированы
        assertEquals(payment.getId(), paymentDTO.getId());
        assertEquals(payment.getPaymentMethod(), paymentDTO.getPaymentMethod());
        assertEquals(payment.getAmount(), paymentDTO.getAmount());
    }

    @Test
    public void testPaymentDTOToPayment() {
        // Создаем объект PaymentDTO
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(1L);
        paymentDTO.setPaymentMethod("Credit Card");
        paymentDTO.setAmount(69.98);

        // Преобразуем его в сущность Payment
        Payment payment = PaymentMapper.INSTANCE.paymentDTOToPayment(paymentDTO);

        // Проверяем, что поля были правильно скопированы
        assertEquals(paymentDTO.getId(), payment.getId());
        assertEquals(paymentDTO.getPaymentMethod(), payment.getPaymentMethod());
        assertEquals(paymentDTO.getAmount(), payment.getAmount());
    }
}

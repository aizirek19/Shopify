package com.ecommerce.Shopify.repositories.test;
import com.ecommerce.Shopify.entities.Order;
import com.ecommerce.Shopify.entities.Payment;

//import com.ecommerce.Shopify.repositories.PaymentRepository;
import com.ecommerce.Shopify.repositories.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;
//    private PaymentRepository paymentRepository;

    @Test
    public void testFindByOrderId() {
        // Arrange
        Order order = new Order();
        Payment payment = new Payment("Credit Card", 100.00, order);
        paymentRepository.save(payment);

        // Act
        List<Payment> foundPayments = paymentRepository.findByOrderId(order.getId());

        // Assert
        assertThat(foundPayments.size()).isEqualTo(1);
        assertThat(foundPayments.get(0).getPaymentMethod()).isEqualTo("Credit Card");
    }
}

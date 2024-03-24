package com.ecommerce.Shopify.repository;

import com.ecommerce.Shopify.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrderId(Long id);
    // Add custom query methods if needed
}

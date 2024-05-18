package com.ecommerce.Shopify.repositories;

import com.ecommerce.Shopify.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("select p from Payment p where p.id = :id")
    List<Payment> findByPaymentId(Long id);
    // Add custom query methods if needed
}

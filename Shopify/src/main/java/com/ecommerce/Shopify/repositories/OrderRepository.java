package com.ecommerce.Shopify.repositories;

import com.ecommerce.Shopify.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long id);
    // Add custom query methods if needed
}

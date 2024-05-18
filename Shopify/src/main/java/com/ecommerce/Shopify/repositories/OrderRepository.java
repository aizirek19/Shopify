package com.ecommerce.Shopify.repositories;

import com.ecommerce.Shopify.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.id = :id")
    List<Order> findOrderById(Long id);
    // Add custom query methods if needed
}

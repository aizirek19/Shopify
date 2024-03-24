package com.ecommerce.Shopify.repository;

import com.ecommerce.Shopify.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String testProduct);
    // Add custom query methods if needed
}

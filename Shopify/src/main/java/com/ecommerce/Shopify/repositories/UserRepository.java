package com.ecommerce.Shopify.repositories;

import com.ecommerce.Shopify.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods if needed
}

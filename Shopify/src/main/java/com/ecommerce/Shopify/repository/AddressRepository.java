package com.ecommerce.Shopify.repository;
import com.ecommerce.Shopify.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserId(Long id);
    // Здесь можно добавить дополнительные методы для работы с адресами, если это необходимо
}

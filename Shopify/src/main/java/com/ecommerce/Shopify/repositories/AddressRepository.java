package com.ecommerce.Shopify.repositories;

import com.ecommerce.Shopify.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where a.id = :id")
    List<Address> findByAddressId(Long id);

}

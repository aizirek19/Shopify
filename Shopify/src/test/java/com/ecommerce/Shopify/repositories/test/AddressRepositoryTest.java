package com.ecommerce.Shopify.repositories.test;

import com.ecommerce.Shopify.entities.Address;
import com.ecommerce.Shopify.entities.User;
//import com.ecommerce.Shopify.repositories.AddressRepository;
import com.ecommerce.Shopify.repositories.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;


    @Test
    public void testFindByUserId() {
        // Arrange
        User user = new User("test_user", "test@example.com", "password123");
        Address address = new Address("123 Main St", "City", "State", "12345", user);
        addressRepository.save(address);

        // Act
        List<Address> foundAddresses = addressRepository.findByUserId(user.getId());

        // Assert
        assertThat(foundAddresses.size()).isEqualTo(1);
        assertThat(foundAddresses.get(0).getStreet()).isEqualTo("123 Main St");
    }
}

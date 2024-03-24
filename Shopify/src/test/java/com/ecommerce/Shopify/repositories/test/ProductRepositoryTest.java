package com.ecommerce.Shopify.repositories.test;
import com.ecommerce.Shopify.entities.Product;
import com.ecommerce.Shopify.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
//    private ProductRepository productRepository;

    @Test
    public void testFindByProductName() {
        // Arrange
        Product product = new Product("Test Product", "Description", 10.99, 100);
        //productRepository.save(product);
        productRepository.save(product);

        // Act
        List<Product> foundProducts = productRepository.findByName("Test Product");

        // Assert
        assertThat(foundProducts.size()).isEqualTo(1);
        assertThat(foundProducts.get(0).getDescription()).isEqualTo("Description");
    }
}

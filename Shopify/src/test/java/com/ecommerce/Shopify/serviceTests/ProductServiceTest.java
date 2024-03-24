package com.ecommerce.Shopify.serviceTests;

import com.ecommerce.Shopify.dto.ProductDTO;
import com.ecommerce.Shopify.entities.Product;
import com.ecommerce.Shopify.exceptions.ProductNotFoundException;
import com.ecommerce.Shopify.mappers.ProductMapper;
//import com.ecommerce.Shopify.repository.ProductRepository;
import com.ecommerce.Shopify.repositories.ProductRepository;
import com.ecommerce.Shopify.service.ProductService;
import com.ecommerce.Shopify.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService = new ProductServiceImpl(productRepository);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName("Test Product");

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        ProductDTO createdProductDTO = productService.createProduct(productDTO);

        assertNotNull(createdProductDTO);
        assertEquals(savedProduct.getId(), createdProductDTO.getId());
        assertEquals(savedProduct.getName(), createdProductDTO.getName());
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Updated Test Product");

        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Test Product");

        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setName("Updated Test Product");

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        ProductDTO updatedProductDTO = productService.updateProduct(productId, productDTO);

        assertNotNull(updatedProductDTO);
        assertEquals(updatedProduct.getId(), updatedProductDTO.getId());
        assertEquals(updatedProduct.getName(), updatedProductDTO.getName());
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;

        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Test Product");

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        assertDoesNotThrow(() -> productService.deleteProduct(productId));
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;

        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Test Product");

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        ProductDTO productDTO = productService.getProductById(productId);

        assertNotNull(productDTO);
        assertEquals(existingProduct.getId(), productDTO.getId());
        assertEquals(existingProduct.getName(), productDTO.getName());
    }

    @Test
    void testGetProductById_ProductNotFound() {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(productId));
    }
}

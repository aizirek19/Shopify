package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.ProductDTO;
import com.ecommerce.Shopify.entities.Product;
import com.ecommerce.Shopify.mappers.ProductMapper;
//import com.ecommerce.Shopify.repositories.ProductRepository;
import com.ecommerce.Shopify.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
        return ProductMapper.toDTO(product);
    }

    @Override
    public void createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long productId, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
        Product updatedProduct = ProductMapper.toEntity(productDTO);
        updatedProduct.setId(existingProduct.getId());
        productRepository.save(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

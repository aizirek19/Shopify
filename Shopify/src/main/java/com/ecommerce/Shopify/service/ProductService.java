package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO getProductById(Long productId);
    List<ProductDTO> getAllProducts();
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long productId, ProductDTO productDTO);
    void deleteProduct(Long productId);
}

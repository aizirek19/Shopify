package com.ecommerce.Shopify.mappers;

import com.ecommerce.Shopify.dto.ProductDTO;
import com.ecommerce.Shopify.entities.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductMapperTest {

    @Test
    public void testProductToProductDTO() {

        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setDescription("Description of Product 1");
        product.setPrice(29.99);
        product.setQuantityAvailable(100);


        ProductDTO productDTO = ProductMapper.INSTANCE.productToProductDTO(product);


        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getDescription(), productDTO.getDescription());
        assertEquals(product.getPrice(), productDTO.getPrice());
        assertEquals(product.getQuantityAvailable(), productDTO.getQuantityAvailable());
    }

    @Test
    public void testProductDTOToProduct() {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Product 1");
        productDTO.setDescription("Description of Product 1");
        productDTO.setPrice(29.99);
        productDTO.setQuantityAvailable(100);


        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);


        assertEquals(productDTO.getId(), product.getId());
        assertEquals(productDTO.getName(), product.getName());
        assertEquals(productDTO.getDescription(), product.getDescription());
        assertEquals(productDTO.getPrice(), product.getPrice());
        assertEquals(productDTO.getQuantityAvailable(), product.getQuantityAvailable());
    }
}

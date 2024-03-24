package com.ecommerce.Shopify.controllerTests;

import com.ecommerce.Shopify.controllers.ProductController;
import com.ecommerce.Shopify.dto.ProductDTO;
//import com.ecommerce.Shopify.services.ProductService;
import com.ecommerce.Shopify.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetProductById() throws Exception {
        Long productId = 1L;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productId);
        productDTO.setName("Product 1");
        productDTO.setDescription("Description of Product 1");
        productDTO.setPrice(29.99);
        productDTO.setQuantityAvailable(100);

        when(productService.getProductById(productId)).thenReturn(productDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/{productId}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.name").value("Product 1"))
                .andExpect(jsonPath("$.description").value("Description of Product 1"))
                .andExpect(jsonPath("$.price").value(29.99))
                .andExpect(jsonPath("$.quantityAvailable").value(100));
    }

    @Test
    public void testCreateProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("New Product");
        productDTO.setDescription("Description of New Product");
        productDTO.setPrice(49.99);
        productDTO.setQuantityAvailable(50);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .content(asJsonString(productDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(productService, times(1)).createProduct(productDTO);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Long productId = 1L;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Updated Product");
        productDTO.setDescription("Description of Updated Product");
        productDTO.setPrice(39.99);
        productDTO.setQuantityAvailable(75);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/{productId}", productId)
                        .content(asJsonString(productDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).updateProduct(productId, productDTO);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Long productId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/{productId}", productId))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).deleteProduct(productId);
    }

    // Вспомогательный метод для преобразования объекта в JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}




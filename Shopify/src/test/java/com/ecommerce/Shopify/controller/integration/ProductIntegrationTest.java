package com.ecommerce.Shopify.controller.integration;

import com.ecommerce.Shopify.controllers.ProductController;
import com.ecommerce.Shopify.dto.ProductDTO;
//import com.ecommerce.Shopify.services.ProductService;
import com.ecommerce.Shopify.service.ProductService;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Test
    public void testCreateProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setPrice(10.99);

        GsonJsonProvider JsonUtils = new GsonJsonProvider();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(productDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    // Add integration tests for other CRUD operations and components
}

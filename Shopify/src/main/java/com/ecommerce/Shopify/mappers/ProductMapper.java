package com.ecommerce.Shopify.mappers;

import com.ecommerce.Shopify.dto.ProductDTO;
import com.ecommerce.Shopify.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    static Product toEntity(ProductDTO productDTO) {
        return null;
    }

    static ProductDTO toDTO(Product product) {
        return null;
    }

    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);
}

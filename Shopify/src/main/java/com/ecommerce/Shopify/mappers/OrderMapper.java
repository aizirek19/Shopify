package com.ecommerce.Shopify.mappers;

import com.ecommerce.Shopify.dto.OrderDTO;
import com.ecommerce.Shopify.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    static Order toEntity(OrderDTO orderDTO) {
        return null;
    }

    @Mapping(source = "userId", target = "user.id")
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(source = "user.id", target = "userId")
    Order orderDTOToOrder(OrderDTO orderDTO);

    static <R> R toDTO(Order order) {
        return null;
    }


}

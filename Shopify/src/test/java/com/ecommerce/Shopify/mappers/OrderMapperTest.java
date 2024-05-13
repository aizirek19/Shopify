package com.ecommerce.Shopify.mappers;

import com.ecommerce.Shopify.dto.OrderDTO;
import com.ecommerce.Shopify.dto.UserDTO;
import com.ecommerce.Shopify.entities.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderMapperTest {

    @Test
    public void testOrderToOrderDTO() {
        // Создаем объект Order
        Order order = new Order();
        order.setId(1L);
        order.setOrderDate(LocalDateTime.now());
        order.setUserId(1L);

        // Преобразуем его в DTO
        OrderDTO orderDTO = OrderMapper.INSTANCE.orderToOrderDTO(order);

        // Проверяем, что поля были правильно скопированы
        assertEquals(order.getId(), orderDTO.getId());
        assertEquals(order.getOrderDate(), orderDTO.getOrderDate());
        assertEquals(order.getUserId(), orderDTO.getUser().getId());
    }

    @Test
    public void testOrderDTOToOrder() {
        // Создаем объект OrderDTO
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setOrderDate(LocalDateTime.now());
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        orderDTO.setUser(userDTO);

        // Преобразуем его в сущность Order
        Order order = OrderMapper.INSTANCE.orderDTOToOrder(orderDTO);

        // Проверяем, что поля были правильно скопированы
        assertEquals(orderDTO.getId(), order.getId());
        assertEquals(orderDTO.getOrderDate(), order.getOrderDate());
        assertEquals(orderDTO.getUser().getId(), order.getUserId());
    }
}

package com.ecommerce.Shopify.serviceTests;

import com.ecommerce.Shopify.dto.OrderDTO;
import com.ecommerce.Shopify.entities.Order;
import com.ecommerce.Shopify.exceptions.OrderNotFoundException;
import com.ecommerce.Shopify.mappers.OrderMapper;
//import com.ecommerce.Shopify.repository.OrderRepository;
import com.ecommerce.Shopify.repositories.OrderRepository;
import com.ecommerce.Shopify.service.OrderService;
import com.ecommerce.Shopify.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService = new OrderServiceImpl(orderRepository);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderName("Test Order");

        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setOrderName("Test Order");

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        OrderDTO createdOrderDTO = (OrderDTO) orderService.createOrder(orderDTO);

        assertNotNull(createdOrderDTO);
        assertEquals(savedOrder.getId(), createdOrderDTO.getId());
        assertEquals(savedOrder.getOrderName(), createdOrderDTO.getOrderName());
    }

    @Test
    void testUpdateOrder() {
        Long orderId = 1L;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderName("Updated Test Order");

        Order existingOrder = new Order();
        existingOrder.setId(orderId);
        existingOrder.setOrderName("Test Order");

        Order updatedOrder = new Order();
        updatedOrder.setId(orderId);
        updatedOrder.setOrderName("Updated Test Order");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(updatedOrder);

        OrderDTO updatedOrderDTO = (OrderDTO) orderService.updateOrder(orderId, orderDTO);

        assertNotNull(updatedOrderDTO);
        assertEquals(updatedOrder.getId(), updatedOrderDTO.getId());
        assertEquals(updatedOrder.getOrderName(), updatedOrderDTO.getOrderName());
    }

    @Test
    void testDeleteOrder() {
        Long orderId = 1L;

        Order existingOrder = new Order();
        existingOrder.setId(orderId);
        existingOrder.setOrderName("Test Order");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));

        assertDoesNotThrow(() -> orderService.deleteOrder(orderId));
    }

    @Test
    void testGetOrderById() {
        Long orderId = 1L;

        Order existingOrder = new Order();
        existingOrder.setId(orderId);
        existingOrder.setOrderName("Test Order");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));

        OrderDTO orderDTO = orderService.getOrderById(orderId);

        assertNotNull(orderDTO);
        assertEquals(existingOrder.getId(), orderDTO.getId());
        assertEquals(existingOrder.getOrderName(), orderDTO.getOrderName());
    }

    @Test
    void testGetOrderById_OrderNotFound() {
        Long orderId = 1L;

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(orderId));
    }
}

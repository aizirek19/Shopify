package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO getOrderById(Long orderId);
    List<OrderDTO> getAllOrders();
    void createOrder(OrderDTO orderDTO);
    void updateOrder(Long orderId, OrderDTO orderDTO);
    void deleteOrder(Long orderId);
}

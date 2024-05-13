package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<Object> getAllOrders();

    OrderDTO getOrderById(Long orderId);

    Object createOrder(OrderDTO orderDTO);

    Object updateOrder(Long orderId, OrderDTO orderDTO);

    void deleteOrder(Long orderId);
}


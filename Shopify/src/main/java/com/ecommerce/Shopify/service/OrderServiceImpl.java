package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.OrderDTO;
import com.ecommerce.Shopify.entities.Order;
import com.ecommerce.Shopify.mappers.OrderMapper;
import com.ecommerce.Shopify.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Object> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        return null;
    }

    @Override
    public void createOrder(OrderDTO orderDTO) {

    }

    @Override
    public void updateOrder(Long orderId, OrderDTO orderDTO) {

    }

    @Override
    public void deleteOrder(Long orderId) {

    }

}

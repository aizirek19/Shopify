package com.ecommerce.Shopify.service;

import com.ecommerce.Shopify.dto.OrderDTO;
import com.ecommerce.Shopify.entities.Order;
import com.ecommerce.Shopify.exceptions.OrderNotFoundException;
import com.ecommerce.Shopify.mappers.OrderMapper;
import com.ecommerce.Shopify.repositories.OrderRepository;
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
    public Object createOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.toEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return OrderMapper.toDTO(savedOrder);
    }

    @Override
    public Object updateOrder(Long orderId, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

        Order updatedOrder = OrderMapper.toEntity(orderDTO);
        updatedOrder.setId(existingOrder.getId());
        Order savedOrder = orderRepository.save(updatedOrder);
        return OrderMapper.toDTO(savedOrder);
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
        orderRepository.delete(order);
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
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
        return OrderMapper.toDTO(order);
    }
}

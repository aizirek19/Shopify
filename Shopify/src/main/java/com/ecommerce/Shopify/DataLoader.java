package com.ecommerce.Shopify;

import com.ecommerce.Shopify.entities.Order;
import com.ecommerce.Shopify.entities.Payment;
import com.ecommerce.Shopify.entities.Product;
import com.ecommerce.Shopify.entities.User;
import com.ecommerce.Shopify.repository.OrderRepository;
import com.ecommerce.Shopify.repository.PaymentRepository;
import com.ecommerce.Shopify.repository.ProductRepository;
import com.ecommerce.Shopify.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, ProductRepository productRepository,
                      OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert sample users
        userRepository.saveAll(Arrays.asList(
                new User("john_doe", "john@example.com", "password123"),
                new User("jane_smith", "jane@example.com", "password456")
        ));

        // Insert sample products
        productRepository.saveAll(Arrays.asList(
                new Product("Product 1", "Description of Product 1", 29.99, 100),
                new Product("Product 2", "Description of Product 2", 39.99, 50)
        ));

        // Insert sample orders
        Order order1 = new Order(LocalDateTime.parse("2024-03-23T10:00:00"), userRepository.findById(1L).get());
        Order order2 = new Order(LocalDateTime.parse("2024-03-24T11:00:00"), userRepository.findById(2L).get());
        orderRepository.saveAll(Arrays.asList(order1, order2));

        // Insert sample products for orders
        order1.setProducts(Arrays.asList(productRepository.findById(1L).get(), productRepository.findById(2L).get()));
        order2.setProducts(Arrays.asList(productRepository.findById(2L).get()));
        orderRepository.saveAll(Arrays.asList(order1, order2));

        // Insert sample payments
        paymentRepository.saveAll(Arrays.asList(
                new Payment("Credit Card", 69.98, order1),
                new Payment("PayPal", 39.99, order2)
        ));
    }
}


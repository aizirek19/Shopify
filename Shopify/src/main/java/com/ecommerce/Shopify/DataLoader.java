package com.ecommerce.Shopify;

import com.ecommerce.Shopify.entities.*;
import com.ecommerce.Shopify.exceptions.ProductNotFoundException;
import com.ecommerce.Shopify.repositories.OrderRepository;
import com.ecommerce.Shopify.repositories.PaymentRepository;
import com.ecommerce.Shopify.repositories.ProductRepository;
import com.ecommerce.Shopify.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
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
        System.out.println(1);
        Order order1 = new Order(LocalDateTime.now(), userRepository.findById(1L).orElseThrow(() -> new UsernameNotFoundException("User not found !")));
        Order order2 = new Order(LocalDateTime.now(), userRepository.findById(2L).orElseThrow(() -> new UsernameNotFoundException("User not found !")));
        System.out.println(2);
        orderRepository.saveAll(Arrays.asList(order1, order2));
        System.out.println(3);
        // Insert sample products for orders
        order1.setProducts(Arrays.asList(productRepository.findById(1L).orElseThrow(() -> new ProductNotFoundException("Product not found !")), productRepository.findById(2L).orElseThrow(() -> new ProductNotFoundException("Product not found !"))));
        order2.setProducts(Arrays.asList(productRepository.findById(1L).orElseThrow(() -> new ProductNotFoundException("Product not found !")), productRepository.findById(2L).orElseThrow(() -> new ProductNotFoundException("Product not found !"))));
        System.out.println(4);
        orderRepository.saveAll(Arrays.asList(order1, order2));
        System.out.println(5);

        // Insert sample payments
        paymentRepository.saveAll(Arrays.asList(
                new Payment("Credit Card", 69.98, order1),
                new Payment("PayPal", 39.99, order2)
        ));
    }
}


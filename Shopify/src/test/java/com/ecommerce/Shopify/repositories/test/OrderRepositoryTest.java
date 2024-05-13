package com.ecommerce.Shopify.repositories.test;
import com.ecommerce.Shopify.entities.Order;
import com.ecommerce.Shopify.entities.User;
//import com.ecommerce.Shopify.repositories.OrderRepository;
import com.ecommerce.Shopify.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
//    private OrderRepository orderRepository;

    @Test
    public void testFindByUserId() {
        // Arrange
        User user = new User("test_user", "test@example.com", "password123");
        Order order = new Order(LocalDateTime.now(), user);
        orderRepository.save(order);

        // Act
        List<Order> foundOrders = orderRepository.findByUserId(user.getId());

        // Assert
        assertThat(foundOrders.size()).isEqualTo(1);
    }
}
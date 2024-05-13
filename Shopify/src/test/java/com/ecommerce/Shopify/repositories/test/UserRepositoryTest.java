package com.ecommerce.Shopify.repositories.test;
import com.ecommerce.Shopify.entities.User;
import com.ecommerce.Shopify.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindById() {
        // Arrange
        User user = new User("test_user", "test@example.com", "password123");
        userRepository.save(user);

        // Act
        Optional<User> foundUser = userRepository.findById(user.getId());

        // Assert
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("test_user");
    }
}

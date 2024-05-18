package com.ecommerce.Shopify.repositories;

import com.ecommerce.Shopify.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = ?1")
    Optional<User> getByEmail(String email);

    @Query("select new User(u.email, u.password, u.role) from User u")
    public List<User> getAllUsers();

}
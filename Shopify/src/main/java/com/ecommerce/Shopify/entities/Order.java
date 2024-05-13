package com.ecommerce.Shopify.entities;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Product> products;

    public Order(LocalDateTime parse, User user) {
    }

    public Order(LocalDateTime orderDate, User user, List<Product> products) {
        this.orderDate = orderDate;
        this.user = user;
        this.products = products;
    }

    public Order() {

    }

    public void setUserId(long l) {
    }

    public short getUserId() {
        return 0;
    }

    public void setOrderName(String testOrder) {
    }

    public short getOrderName() {
        return 0;
    }
}

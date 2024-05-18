package com.ecommerce.Shopify.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private int quantityAvailable;

    @ManyToOne
    private User seller;

    @ManyToMany(mappedBy = "products")
    private List<Order> orderList;

    public Product() {
    }

    public Product(String name, String description, double price, int quantityAvailable) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    public Product(String s, double v) {
    }
}

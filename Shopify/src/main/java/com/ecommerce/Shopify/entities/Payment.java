package com.ecommerce.Shopify.entities;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentMethod;
    private double amount;

    @ManyToOne
    private Order order;

    public Payment() {
    }

    public Payment(String paymentMethod, double amount, Order order) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.order = order;
    }

    public void setOrderId(long l) {
    }
}


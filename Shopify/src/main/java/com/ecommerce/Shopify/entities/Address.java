package com.ecommerce.Shopify.entities;
import lombok.Data;

import jakarta.persistence.*;
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zipCode;

    @ManyToOne
    private User user;

    public Address() {
    }

    public Address(String street, String city, String state, String zipCode, User user) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.user = user;
    }

    public Address(long l, String s, String s1) {
    }

    public void setCountry(String testCountry) {
    }

    public short getCountry() {

        return 0;
    }
}

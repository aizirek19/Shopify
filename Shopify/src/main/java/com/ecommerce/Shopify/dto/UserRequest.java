package com.ecommerce.Shopify.dto;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UserRequest {

    private String email;
    private String password;
    private String name;

}

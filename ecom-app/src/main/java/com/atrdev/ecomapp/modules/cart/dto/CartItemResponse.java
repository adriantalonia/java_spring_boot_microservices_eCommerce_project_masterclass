package com.atrdev.ecomapp.modules.cart.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemResponse {

    private Long id;

    private Long userId;
    private String username;

    private Long productId;
    private String productName;
    private String productDescription;

    private Integer quantity;
    private BigDecimal price;
}

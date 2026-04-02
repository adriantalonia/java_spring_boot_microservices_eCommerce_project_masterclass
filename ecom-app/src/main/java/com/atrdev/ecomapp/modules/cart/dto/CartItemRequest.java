package com.atrdev.ecomapp.modules.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequest {
    @NotNull(message = "productId is required")
    private Long productId;
    @Min(value = 1, message = "quantity greater than 0 than 0")
    private Integer quantity;
}

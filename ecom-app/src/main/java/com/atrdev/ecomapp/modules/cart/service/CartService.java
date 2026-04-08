package com.atrdev.ecomapp.modules.cart.service;

import com.atrdev.ecomapp.modules.cart.dto.CartItemRequest;
import com.atrdev.ecomapp.modules.cart.dto.CartItemResponse;

public interface CartService {
    CartItemResponse addToCart(String userId, CartItemRequest request);

    void deleteItemFromCart(String userId, String productId);
}

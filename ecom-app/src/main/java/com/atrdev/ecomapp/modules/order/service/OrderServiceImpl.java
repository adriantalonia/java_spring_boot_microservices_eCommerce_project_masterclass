package com.atrdev.ecomapp.modules.order.service;

import com.atrdev.ecomapp.modules.cart.dto.CartItemResponse;
import com.atrdev.ecomapp.modules.cart.entity.CartItem;
import com.atrdev.ecomapp.modules.cart.service.CartService;
import com.atrdev.ecomapp.modules.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CartService cartService;

    @Override
    public OrderResponse createOrder(String userId) {
        // Validate cart items
        List<CartItemResponse> cartItems = cartService.getCart(userId);
        // Validate user

        // Calculate total price

        // create order

        // clear cart

        return null;
    }
}

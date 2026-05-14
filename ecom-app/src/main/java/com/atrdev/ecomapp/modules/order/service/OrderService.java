package com.atrdev.ecomapp.modules.order.service;

import com.atrdev.ecomapp.modules.order.dto.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(String userId);
}

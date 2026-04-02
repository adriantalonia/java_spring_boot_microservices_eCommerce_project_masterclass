package com.atrdev.ecomapp.shared.exception;

import lombok.Getter;

@Getter
public class InsufficientStockException extends RuntimeException {
    private final Long productId;
    private final Integer availableStock;
    private final Integer requestedQuantity;

    public InsufficientStockException(Long productId, Integer availableStock, Integer requestedQuantity) {
        super(String.format("Insufficient stock for product id %d. Available: %d, Requested: %d",
                productId, availableStock, requestedQuantity));
        this.productId = productId;
        this.availableStock = availableStock;
        this.requestedQuantity = requestedQuantity;
    }
}

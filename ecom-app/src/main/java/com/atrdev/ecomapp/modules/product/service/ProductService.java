package com.atrdev.ecomapp.modules.product.service;

import com.atrdev.ecomapp.modules.product.dto.ProductRequest;
import com.atrdev.ecomapp.modules.product.dto.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
}

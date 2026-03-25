package com.atrdev.ecomapp.modules.product.service;

import com.atrdev.ecomapp.modules.product.dto.ProductRequest;
import com.atrdev.ecomapp.modules.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}

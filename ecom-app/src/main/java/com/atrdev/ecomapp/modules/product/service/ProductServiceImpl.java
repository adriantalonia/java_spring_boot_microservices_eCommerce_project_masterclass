package com.atrdev.ecomapp.modules.product.service;

import com.atrdev.ecomapp.modules.product.dto.ProductRequest;
import com.atrdev.ecomapp.modules.product.dto.ProductResponse;
import com.atrdev.ecomapp.modules.product.entity.Product;
import com.atrdev.ecomapp.modules.product.mapper.ProductMapper;
import com.atrdev.ecomapp.modules.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = mapper.toProductEntity(productRequest);
        return  mapper.toProductResponse(productRepository.save(product));
    }


}

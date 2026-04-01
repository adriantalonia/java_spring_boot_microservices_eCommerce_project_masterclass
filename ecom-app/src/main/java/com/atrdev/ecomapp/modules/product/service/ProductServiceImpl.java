package com.atrdev.ecomapp.modules.product.service;

import com.atrdev.ecomapp.modules.product.dto.ProductRequest;
import com.atrdev.ecomapp.modules.product.dto.ProductResponse;
import com.atrdev.ecomapp.modules.product.entity.Product;
import com.atrdev.ecomapp.modules.product.mapper.ProductMapper;
import com.atrdev.ecomapp.modules.product.repository.ProductRepository;
import com.atrdev.ecomapp.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = mapper.toProductEntity(productRequest);
        return mapper.toProductResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    mapper.updateEntityFromRequest(productRequest, existingProduct);
                    //productRepository.save(existingProduct); dirty checking
                    return mapper.toProductResponse(existingProduct);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        return mapper.toProductResponseList(productRepository.findByActiveTrue());
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", id));
        product.setActive(false);
        //productRepository.save(product); dirty checking
    }

    @Override
    public List<ProductResponse> searchProducts(String keyword) {
        return mapper.toProductResponseList(productRepository.searchProducts(keyword));
    }

}

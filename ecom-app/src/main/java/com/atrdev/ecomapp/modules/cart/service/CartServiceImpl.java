package com.atrdev.ecomapp.modules.cart.service;

import com.atrdev.ecomapp.modules.cart.dto.CartItemRequest;
import com.atrdev.ecomapp.modules.cart.dto.CartItemResponse;
import com.atrdev.ecomapp.modules.cart.entity.CartItem;
import com.atrdev.ecomapp.modules.cart.mapper.CartMapper;
import com.atrdev.ecomapp.modules.cart.repository.CartRepository;
import com.atrdev.ecomapp.modules.product.entity.Product;
import com.atrdev.ecomapp.modules.product.repository.ProductRepository;
import com.atrdev.ecomapp.modules.user.entity.User;
import com.atrdev.ecomapp.modules.user.repository.UserRepository;
import com.atrdev.ecomapp.shared.exception.InsufficientStockException;
import com.atrdev.ecomapp.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;

    @Override
    @Transactional
    public CartItemResponse addToCart(String userId, CartItemRequest request) {
        final Long productId = request.getProductId();
        final Long userLongId = Long.valueOf(userId);
        final Integer quantity = request.getQuantity();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", productId));

        User user = userRepository.findById(userLongId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userLongId));

        Optional<CartItem> existingCartItem = cartRepository.findByUserAndProduct(user, product);

        CartItem cartItem = existingCartItem
                .map(existing ->  updateExistingCartItem(existing, product, quantity))
                .orElseGet(() -> createNewCartItem(user, product, quantity));

        return cartMapper.toCartItemResponse(cartRepository.save(cartItem));
    }

    @Override
    @Transactional
    public void deleteItemFromCart(String userId, String productId) {
        final Long productLongId = Long.valueOf(productId);
        final Long userLongId = Long.valueOf(userId);
        Product product = productRepository.findById(productLongId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", productLongId));

        User user = userRepository.findById(userLongId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userLongId));

        cartRepository.deleteByUserAndProduct(user, product);
    }

    @Override
    public List<CartItemResponse> getCart(String userId) {
        final Long userLongId = Long.valueOf(userId);
        User user = userRepository.findById(userLongId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userLongId));
        return cartMapper.toCartItemResponses(cartRepository.findByUser(user));
    }

    private CartItem updateExistingCartItem(CartItem existing, Product product, int quantity) {
        validateStock(product, quantity, existing.getQuantity());
        existing.setQuantity(existing.getQuantity() + quantity);
        existing.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existing.getQuantity())));
        return existing;
    }

    private CartItem createNewCartItem(User user, Product product, int quantity) {
        validateStock(product, quantity, 0);
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        return cartItem;
    }

    private void validateStock(Product product, int requestedQty, int existingQty) {
        int totalQuantity = existingQty + requestedQty;
        if (product.getStockQuantity() < totalQuantity) {
            throw new InsufficientStockException(product.getId(), product.getStockQuantity(), totalQuantity);
        }
    }
}

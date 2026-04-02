package com.atrdev.ecomapp.modules.cart.repository;

import com.atrdev.ecomapp.modules.cart.entity.CartItem;
import com.atrdev.ecomapp.modules.product.entity.Product;
import com.atrdev.ecomapp.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByUserAndProduct(User user, Product product);
}

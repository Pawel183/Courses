package com.embarkx.repo;

import com.embarkx.model.Cart;
import com.embarkx.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = ?2 AND ci.product.productId = ?1")
    CartItem findCartItemByProductIdAndCartId(Long productId, Long cartId);

    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.cart.cartId = ?1 AND c.product.productId = ?2")
    void deleteCartItemByProductIdAndCartId(Long cartId, Long productId);
}

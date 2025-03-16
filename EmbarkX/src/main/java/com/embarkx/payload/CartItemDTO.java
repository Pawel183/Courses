package com.embarkx.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private Long cartId;
    private CartDTO cart;
    private ProductDTO product;
    private Integer quantity;
    private double discount;
    private double productPrice;
}

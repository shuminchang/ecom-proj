package com.telusko.ecom_proj.dto.cart;

import com.telusko.ecom_proj.model.Cart;

import java.util.List;

public class CartDto {
    private List<CartItemDto> cartItem;
    private double totalCost;

    public CartDto() {
    }

    public List<CartItemDto> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItemDto> cartItem) {
        this.cartItem = cartItem;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}

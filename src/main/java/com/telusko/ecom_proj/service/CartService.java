package com.telusko.ecom_proj.service;

import com.telusko.ecom_proj.dto.cart.AddToCartDto;
import com.telusko.ecom_proj.dto.cart.CartDto;
import com.telusko.ecom_proj.dto.cart.CartItemDto;
import com.telusko.ecom_proj.exceptions.CustomException;
import com.telusko.ecom_proj.model.Cart;
import com.telusko.ecom_proj.model.Product;
import com.telusko.ecom_proj.model.User;
import com.telusko.ecom_proj.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    ProductService productService;

    @Autowired
    CartRepo cartRepo;
    public void addToCart(AddToCartDto addToCartDto, User user) {
        // validate if the product id is valid
        Product product = productService.findById(addToCartDto.getProductId());

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());

        // save the cart
        cartRepo.save(cart);
    }

    public CartDto listCartItem(User user) {
        List<Cart> cartList = cartRepo.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0;
        for (Cart cart: cartList) {
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItems.add(cartItemDto);
        }

        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItem(cartItems);
        return cartDto;
    }

    public void deleteCartItem(Integer cartItemId, User user) {
        // the item id belongs to user

        Optional<Cart> optionalCart = cartRepo.findById(cartItemId);

        if (optionalCart.isEmpty()) {
            throw new CustomException("cart item id is invalid: " + cartItemId);
        }

        Cart cart = optionalCart.get();

        if (cart.getUser() != user) {
            throw new CustomException("cart item does not belong to user: " + cartItemId);
        }

        cartRepo.delete(cart);
    }
}

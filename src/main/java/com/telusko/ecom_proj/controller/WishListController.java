package com.telusko.ecom_proj.controller;

import com.telusko.ecom_proj.common.ApiResponse;
import com.telusko.ecom_proj.dto.ProductDto;
import com.telusko.ecom_proj.model.Product;
import com.telusko.ecom_proj.model.User;
import com.telusko.ecom_proj.model.WishList;
import com.telusko.ecom_proj.service.AuthenticationService;
import com.telusko.ecom_proj.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    WishListService wishListService;

    @Autowired
    AuthenticationService authenticationService;
    // save product in wishlist item
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
                                                     @RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        // save the item in wishlist
        WishList wishList = new WishList(user, product);

        wishListService.createWishList(wishList);

        ApiResponse apiResponse = new ApiResponse(true, "Added to wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    // get all wishlist item for a user
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {

        // authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);
        List<ProductDto> productDtos = wishListService.getWishListForUser(user);

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
}

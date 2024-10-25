package com.telusko.ecom_proj.service;

import com.telusko.ecom_proj.dto.ProductDto;
import com.telusko.ecom_proj.model.User;
import com.telusko.ecom_proj.model.WishList;
import com.telusko.ecom_proj.repo.WishListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    WishListRepo wishListRepo;

    @Autowired
    ProductService productService;

    public void createWishList(WishList wishList) {
        wishListRepo.save(wishList);
    }

    public List<ProductDto> getWishListForUser(User user) {
        final List<WishList> wishLists = wishListRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for (WishList wishList : wishLists) {
            productDtos.add(productService.getProductDto(wishList.getProduct()));
        }

        return productDtos;
    }
}

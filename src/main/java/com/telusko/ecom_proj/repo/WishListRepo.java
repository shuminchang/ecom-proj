package com.telusko.ecom_proj.repo;

import com.telusko.ecom_proj.model.User;
import com.telusko.ecom_proj.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepo extends JpaRepository<WishList, Integer> {

    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}

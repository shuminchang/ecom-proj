package com.telusko.ecom_proj.repo;

import com.telusko.ecom_proj.model.AuthenticationToken;
import com.telusko.ecom_proj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {

    AuthenticationToken findByUser(User user);
}

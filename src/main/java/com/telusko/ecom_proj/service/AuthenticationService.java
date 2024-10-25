package com.telusko.ecom_proj.service;

import com.telusko.ecom_proj.exceptions.AuthenticationFailException;
import com.telusko.ecom_proj.model.AuthenticationToken;
import com.telusko.ecom_proj.model.User;
import com.telusko.ecom_proj.repo.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepo tokenRepo;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepo.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }

    public User getUser(String token) {
        AuthenticationToken authenticationToken = tokenRepo.findByToken(token);
        if (Objects.isNull(authenticationToken)) {
            return null;
        }
        // authenticationToken is not null
        return authenticationToken.getUser();
    }

    public void authenticate(String token) {
        // null check
        if (Objects.isNull(token)) {
            // throw an exception
            throw new AuthenticationFailException("token not present");
        }
        if (Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("token not valid");
        }
    }
}

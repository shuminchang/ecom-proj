package com.telusko.ecom_proj.controller;

import com.telusko.ecom_proj.dto.ResponseDto;
import com.telusko.ecom_proj.dto.user.SignInDto;
import com.telusko.ecom_proj.dto.user.SignInResponseDto;
import com.telusko.ecom_proj.dto.user.SignupDto;
import com.telusko.ecom_proj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    // sign up
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }
    // sign in
    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }

}

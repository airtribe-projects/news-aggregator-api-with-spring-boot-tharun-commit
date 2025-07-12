package com.example.NewsAggregatorAPIwithSpringBoot.Controllers;

import com.example.NewsAggregatorAPIwithSpringBoot.Entity.AuthRequest;
import com.example.NewsAggregatorAPIwithSpringBoot.Entity.UserDto;
import com.example.NewsAggregatorAPIwithSpringBoot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        return userService.login(request);
    }
}
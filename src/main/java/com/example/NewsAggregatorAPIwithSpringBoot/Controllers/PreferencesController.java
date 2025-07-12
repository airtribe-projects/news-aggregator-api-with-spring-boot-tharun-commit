package com.example.NewsAggregatorAPIwithSpringBoot.Controllers;

import com.example.NewsAggregatorAPIwithSpringBoot.Entity.PreferencesDto;
import com.example.NewsAggregatorAPIwithSpringBoot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PreferencesController {

    @Autowired
    private UserService userService;

    @GetMapping("/preferences")
    public ResponseEntity<?> getPreferences(Authentication auth) {
        return userService.getPreferences(auth.getName());
    }

    @PutMapping("/preferences")
    public ResponseEntity<?> updatePreferences(Authentication auth, @RequestBody PreferencesDto dto) {
        return userService.updatePreferences(auth.getName(), dto);
    }

    @GetMapping("/news")
    public ResponseEntity<?> getNews(Authentication auth) {
        return userService.fetchNews(auth.getName());
    }
}
package com.example.NewsAggregatorAPIwithSpringBoot.Service;

import com.example.NewsAggregatorAPIwithSpringBoot.Entity.AuthRequest;
import com.example.NewsAggregatorAPIwithSpringBoot.Entity.PreferencesDto;
import com.example.NewsAggregatorAPIwithSpringBoot.Entity.User;
import com.example.NewsAggregatorAPIwithSpringBoot.Entity.UserDto;
import com.example.NewsAggregatorAPIwithSpringBoot.Repository.UserRepository;
import com.example.NewsAggregatorAPIwithSpringBoot.Util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private NewsService newsService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<?> register(UserDto dto) {
        if (repo.findByUsername(dto.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setPreferences("{}"); // default empty preferences
        repo.save(user);

        return ResponseEntity.ok("Registered successfully");
    }

    public ResponseEntity<?> login(AuthRequest request) {
        User user = repo.findByUsername(request.getUsername()).orElse(null);

        if (user == null || !encoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    public ResponseEntity<?> getPreferences(String username) {
        User user = repo.findByUsername(username).orElseThrow();

        return ResponseEntity.ok(user.getPreferences());
    }

    public ResponseEntity<?> updatePreferences(String username, PreferencesDto dto) {
        User user = repo.findByUsername(username).orElseThrow();

        try {
            String prefsJson = objectMapper.writeValueAsString(dto);
            user.setPreferences(prefsJson);
            repo.save(user);
            return ResponseEntity.ok("Preferences updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid preference data");
        }
    }

    public ResponseEntity<?> fetchNews(String username) {
        User user = repo.findByUsername(username).orElseThrow();

        PreferencesDto prefs;
        try {
            prefs = objectMapper.readValue(user.getPreferences(), PreferencesDto.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid preference format");
        }

        return ResponseEntity.ok(newsService.fetchNews(prefs.getCategory(), prefs.getCountry()));
    }
}
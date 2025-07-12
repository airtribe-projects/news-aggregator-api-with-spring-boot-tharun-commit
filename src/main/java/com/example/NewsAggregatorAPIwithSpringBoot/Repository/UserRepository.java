package com.example.NewsAggregatorAPIwithSpringBoot.Repository;

import com.example.NewsAggregatorAPIwithSpringBoot.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
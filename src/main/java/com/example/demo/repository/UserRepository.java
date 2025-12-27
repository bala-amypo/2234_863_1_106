package com.example.demo.repository;

import com.example.demo.entity.User;
import java.util.Optional;
import java.util.List;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    Optional<User> findByEmail(String email);
}
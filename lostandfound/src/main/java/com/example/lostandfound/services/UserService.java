package com.example.lostandfound.services;

import com.example.lostandfound.entities.Role;
import com.example.lostandfound.entities.User;
import com.example.lostandfound.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * gmaistrelis - Lost and Found - Service for user operations
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Register a new user with encrypted password
     */
    public User registerUser(String username, String password, String email, String fullname, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setFullname(fullname);
        user.setRole(role);
        user.setRegistrationdate(LocalDateTime.now());
        return userRepository.save(user);
    }

    /**
     * Authenticate user by username and password
     */
    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    /**
     * Find user by username
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Find user by email
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Get all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Find user by id
     */
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}

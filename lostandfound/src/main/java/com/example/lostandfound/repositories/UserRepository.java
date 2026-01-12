package com.example.lostandfound.repositories;

import com.example.lostandfound.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * gmaistrelis - Lost and Found - Repository for user data access
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find user by username for login authentication
     */
    User findByUsername(String username);

    /**
     * Find user by email for registration validation
     */
    User findByEmail(String email);
}

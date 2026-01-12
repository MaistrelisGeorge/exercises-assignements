package com.example.lostandfound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * gmaistrelis - Lost and Found - Main application entry point
 */
@SpringBootApplication
public class LostAndFoundApplication {

    public static void main(String[] args) {
        SpringApplication.run(LostAndFoundApplication.class, args);
    }

    /**
     * BCrypt password encoder bean for secure password hashing
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

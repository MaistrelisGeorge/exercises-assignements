package com.example.lostandfound.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * gmaistrelis - Lost and Found - Utility to generate BCrypt password hashes for initial users
 */
public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Hash for 'admin123': " + encoder.encode("admin123"));
        System.out.println("Hash for 'member123': " + encoder.encode("member123"));
    }
}

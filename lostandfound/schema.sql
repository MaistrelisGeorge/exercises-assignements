-- Lost and Found Application Database Schema
-- MySQL Database

CREATE DATABASE IF NOT EXISTS lostandfound;
USE lostandfound;

-- Users table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100),
    role VARCHAR(20) NOT NULL,
    registration_date DATETIME,
    INDEX idx_username (username),
    INDEX idx_email (email)
);

-- Items table
CREATE TABLE items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    category VARCHAR(20) NOT NULL,
    date_lost_found DATE,
    location VARCHAR(200) NOT NULL,
    status VARCHAR(10) NOT NULL,
    contact_info VARCHAR(100),
    date_reported DATETIME,
    reporter_id INT,
    FOREIGN KEY (reporter_id) REFERENCES users(user_id) ON DELETE SET NULL,
    INDEX idx_category (category),
    INDEX idx_status (status),
    INDEX idx_location (location),
    INDEX idx_date_reported (date_reported)
);

-- Comments table
CREATE TABLE comments (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    item_id INT,
    user_id INT,
    comment_text TEXT,
    comment_date DATETIME(6),
    FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL,
    INDEX idx_item_id (item_id),
    INDEX idx_comment_date (comment_date)
);

-- Insert sample admin user
-- Password is 'admin123' hashed with BCrypt
INSERT INTO users (username, password, email, full_name, role, registration_date)
VALUES ('admin', '$2a$10$YourBCryptHashedPasswordHere', 'admin@lostandfound.com', 'System Administrator', 'ADMIN', NOW());

-- Insert sample member user
-- Password is 'member123' hashed with BCrypt
INSERT INTO users (username, password, email, full_name, role, registration_date)
VALUES ('john', '$2a$10$YourBCryptHashedPasswordHere', 'john@example.com', 'John Doe', 'MEMBER', NOW());

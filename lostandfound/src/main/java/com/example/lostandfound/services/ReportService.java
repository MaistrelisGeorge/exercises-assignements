package com.example.lostandfound.services;

import com.example.lostandfound.entities.*;
import com.example.lostandfound.repositories.ItemRepository;
import com.example.lostandfound.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * gmaistrelis - Lost and Found - Service for analytics and reporting
 */
@Service
public class ReportService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Get total count of items by status
     */
    public Map<String, Long> getItemCountByStatus() {
        List<Item> allItems = itemRepository.findAll();
        Map<String, Long> counts = new HashMap<>();
        counts.put("LOST", allItems.stream().filter(i -> i.getStatus() == ItemStatus.LOST).count());
        counts.put("FOUND", allItems.stream().filter(i -> i.getStatus() == ItemStatus.FOUND).count());
        return counts;
    }

    /**
     * Get item count by category
     */
    public Map<String, Long> getItemCountByCategory() {
        List<Item> allItems = itemRepository.findAll();
        return allItems.stream()
                .collect(Collectors.groupingBy(
                    i -> i.getCategory().name(),
                    Collectors.counting()
                ));
    }

    /**
     * Get most active users (users with most items reported)
     */
    public List<Map<String, Object>> getMostActiveUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(user -> {
                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("username", user.getUsername());
                    userInfo.put("fullname", user.getFullname());
                    userInfo.put("itemCount", user.getItems().size());
                    return userInfo;
                })
                .sorted((a, b) -> ((Integer)b.get("itemCount")).compareTo((Integer)a.get("itemCount")))
                .limit(10)
                .collect(Collectors.toList());
    }

    /**
     * Get item count by location
     */
    public Map<String, Long> getItemCountByLocation() {
        List<Item> allItems = itemRepository.findAll();
        return allItems.stream()
                .collect(Collectors.groupingBy(
                    Item::getLocation,
                    Collectors.counting()
                ));
    }

    /**
     * Get recent items
     */
    public List<Item> getRecentItems(int limit) {
        List<Item> allItems = itemRepository.findAll();
        return allItems.stream()
                .sorted((a, b) -> b.getDatereported().compareTo(a.getDatereported()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}

package com.example.lostandfound.services;

import com.example.lostandfound.entities.Category;
import com.example.lostandfound.entities.Item;
import com.example.lostandfound.entities.ItemStatus;
import com.example.lostandfound.entities.User;
import com.example.lostandfound.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * gmaistrelis - Lost and Found - Service for item operations
 */
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    /**
     * Save or update an item
     */
    public Item saveItem(Item item) {
        if (item.getDatereported() == null) {
            item.setDatereported(LocalDateTime.now());
        }
        return itemRepository.save(item);
    }

    /**
     * Find item by id
     */
    public Item findById(Integer id) {
        return itemRepository.findById(id).orElse(null);
    }

    /**
     * Get all items
     */
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /**
     * Find items by category
     */
    public List<Item> findByCategory(Category category) {
        return itemRepository.findByCategory(category);
    }

    /**
     * Find items by status
     */
    public List<Item> findByStatus(ItemStatus status) {
        return itemRepository.findByStatus(status);
    }

    /**
     * Find items by reporter
     */
    public List<Item> findByReporter(User reporter) {
        return itemRepository.findByReporter(reporter);
    }

    /**
     * Find items by location
     */
    public List<Item> findByLocation(String location) {
        return itemRepository.findByLocationContainingIgnoreCase(location);
    }

    /**
     * Search items with multiple criteria
     */
    public List<Item> searchItems(Category category, ItemStatus status, String location,
                                  LocalDate startdate, LocalDate enddate) {
        return itemRepository.searchItems(category, status, location, startdate, enddate);
    }

    /**
     * Delete item by id
     */
    public void deleteItem(Integer id) {
        itemRepository.deleteById(id);
    }
}

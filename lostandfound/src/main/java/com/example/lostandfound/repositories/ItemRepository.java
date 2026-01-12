package com.example.lostandfound.repositories;

import com.example.lostandfound.entities.Category;
import com.example.lostandfound.entities.Item;
import com.example.lostandfound.entities.ItemStatus;
import com.example.lostandfound.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * gmaistrelis - Lost and Found - Repository for item data access
 */
public interface ItemRepository extends JpaRepository<Item, Integer> {

    /**
     * Find items by category
     */
    List<Item> findByCategory(Category category);

    /**
     * Find items by status (lost or found)
     */
    List<Item> findByStatus(ItemStatus status);

    /**
     * Find items by reporter
     */
    List<Item> findByReporter(User reporter);

    /**
     * Find items by location containing keyword
     */
    List<Item> findByLocationContainingIgnoreCase(String location);

    /**
     * Find items reported between dates
     */
    List<Item> findByDatelostfoundBetween(LocalDate startdate, LocalDate enddate);

    /**
     * Search items with multiple criteria
     */
    @Query("SELECT i FROM Item i WHERE " +
           "(:category IS NULL OR i.category = :category) AND " +
           "(:status IS NULL OR i.status = :status) AND " +
           "(:location IS NULL OR LOWER(i.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
           "(:startdate IS NULL OR i.datelostfound >= :startdate) AND " +
           "(:enddate IS NULL OR i.datelostfound <= :enddate)")
    List<Item> searchItems(@Param("category") Category category,
                          @Param("status") ItemStatus status,
                          @Param("location") String location,
                          @Param("startdate") LocalDate startdate,
                          @Param("enddate") LocalDate enddate);
}

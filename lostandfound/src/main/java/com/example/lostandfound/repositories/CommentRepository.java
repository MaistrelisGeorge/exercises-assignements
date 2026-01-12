package com.example.lostandfound.repositories;

import com.example.lostandfound.entities.Comment;
import com.example.lostandfound.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * gmaistrelis - Lost and Found - Repository for comment data access
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * Find all comments for a specific item
     */
    List<Comment> findByItemOrderByCommentdateDesc(Item item);

    /**
     * Find comments by item id
     */
    List<Comment> findByItem_IdOrderByCommentdateDesc(Integer itemid);
}

package com.example.lostandfound.services;

import com.example.lostandfound.entities.Comment;
import com.example.lostandfound.entities.Item;
import com.example.lostandfound.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * gmaistrelis - Lost and Found - Service for comment operations
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * Save a new comment
     */
    public Comment saveComment(Comment comment) {
        if (comment.getCommentdate() == null) {
            comment.setCommentdate(Instant.now());
        }
        return commentRepository.save(comment);
    }

    /**
     * Find all comments for an item
     */
    public List<Comment> findByItem(Item item) {
        return commentRepository.findByItemOrderByCommentdateDesc(item);
    }

    /**
     * Find comments by item id
     */
    public List<Comment> findByItemId(Integer itemid) {
        return commentRepository.findByItem_IdOrderByCommentdateDesc(itemid);
    }

    /**
     * Delete comment by id
     */
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    /**
     * Find comment by id
     */
    public Comment findById(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }
}

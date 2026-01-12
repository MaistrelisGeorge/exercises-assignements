package com.example.lostandfound.entities;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * gmaistrelis - Lost and Found - Represents a comment on an item in the discussion board
 */
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "comment_text", columnDefinition = "TEXT")
    private String commenttext;

    @Column(name = "comment_date")
    private Instant commentdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommenttext() {
        return commenttext;
    }

    public void setCommenttext(String commenttext) {
        this.commenttext = commenttext;
    }

    public Instant getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Instant commentdate) {
        this.commentdate = commentdate;
    }
}

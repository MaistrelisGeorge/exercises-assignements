package com.example.lostandfound.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * gmaistrelis - Lost and Found - Represents a lost or found item report
 */
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer id;

    @NotBlank(message = "Item name is required")
    @Size(max = 100, message = "Item name cannot exceed 100 characters")
    @Column(name = "item_name", nullable = false, length = 100)
    private String itemname;

    @NotBlank(message = "Description is required")
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 20)
    private Category category;

    @Column(name = "date_lost_found")
    private LocalDate datelostfound;

    @NotBlank(message = "Location is required")
    @Column(name = "location", length = 200)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private ItemStatus status;

    @Column(name = "contact_info", length = 100)
    private String contactinfo;

    @Column(name = "date_reported")
    private LocalDateTime datereported;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @OneToMany(mappedBy = "item")
    private Set<Comment> comments = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDatelostfound() {
        return datelostfound;
    }

    public void setDatelostfound(LocalDate datelostfound) {
        this.datelostfound = datelostfound;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public String getContactinfo() {
        return contactinfo;
    }

    public void setContactinfo(String contactinfo) {
        this.contactinfo = contactinfo;
    }

    public LocalDateTime getDatereported() {
        return datereported;
    }

    public void setDatereported(LocalDateTime datereported) {
        this.datereported = datereported;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}

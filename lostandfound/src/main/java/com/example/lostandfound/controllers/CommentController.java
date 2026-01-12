package com.example.lostandfound.controllers;

import com.example.lostandfound.entities.Comment;
import com.example.lostandfound.entities.Item;
import com.example.lostandfound.entities.Role;
import com.example.lostandfound.entities.User;
import com.example.lostandfound.services.CommentService;
import com.example.lostandfound.services.ItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;

/**
 * gmaistrelis - Lost and Found - Controller for discussion board comments
 */
@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ItemService itemService;

    /**
     * Post a new comment on an item
     */
    @PostMapping("/add")
    public String addComment(@RequestParam("itemid") Integer itemid,
                            @RequestParam("commenttext") String commenttext,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("loggedinuser");
        Item item = itemService.findById(itemid);

        if (item == null) {
            return "redirect:/items/all";
        }

        Comment comment = new Comment();
        comment.setItem(item);
        comment.setUser(user);
        comment.setCommenttext(commenttext);
        comment.setCommentdate(Instant.now());

        commentService.saveComment(comment);
        redirectAttributes.addFlashAttribute("message", "Comment added successfully!");
        return "redirect:/items/view/" + itemid;
    }

    /**
     * Delete a comment (admin or comment owner)
     */
    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable Integer id,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        Comment comment = commentService.findById(id);
        User user = (User) session.getAttribute("loggedinuser");

        if (comment == null) {
            return "redirect:/items/all";
        }

        // Check if user is admin or comment owner
        if (user.getRole() == Role.ADMIN || comment.getUser().getId().equals(user.getId())) {
            Integer itemid = comment.getItem().getId();
            commentService.deleteComment(id);
            redirectAttributes.addFlashAttribute("message", "Comment deleted successfully!");
            return "redirect:/items/view/" + itemid;
        }

        return "redirect:/items/all";
    }
}

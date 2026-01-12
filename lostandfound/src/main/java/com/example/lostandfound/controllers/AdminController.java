package com.example.lostandfound.controllers;

import com.example.lostandfound.entities.Role;
import com.example.lostandfound.entities.User;
import com.example.lostandfound.services.ItemService;
import com.example.lostandfound.services.ReportService;
import com.example.lostandfound.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * gmaistrelis - Lost and Found - Controller for admin panel operations
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ReportService reportService;

    /**
     * Show admin dashboard with analytics
     */
    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session, ModelMap model) {
        User user = (User) session.getAttribute("loggedinuser");

        // Check if user is admin
        if (user.getRole() != Role.ADMIN) {
            return "redirect:/dashboard";
        }

        model.addAttribute("itemcountbystatus", reportService.getItemCountByStatus());
        model.addAttribute("itemcountbycategory", reportService.getItemCountByCategory());
        model.addAttribute("mostactiveusers", reportService.getMostActiveUsers());
        model.addAttribute("itemcountbylocation", reportService.getItemCountByLocation());
        model.addAttribute("recentitems", reportService.getRecentItems(10));
        model.addAttribute("totalusers", userService.getAllUsers().size());
        model.addAttribute("totalitems", itemService.getAllItems().size());

        return "admindashboard";
    }

    /**
     * Show all users
     */
    @GetMapping("/users")
    public String manageUsers(HttpSession session, ModelMap model) {
        User user = (User) session.getAttribute("loggedinuser");

        if (user.getRole() != Role.ADMIN) {
            return "redirect:/dashboard";
        }

        model.addAttribute("users", userService.getAllUsers());
        return "adminusers";
    }

    /**
     * Show all items for admin management
     */
    @GetMapping("/items")
    public String manageItems(HttpSession session, ModelMap model) {
        User user = (User) session.getAttribute("loggedinuser");

        if (user.getRole() != Role.ADMIN) {
            return "redirect:/dashboard";
        }

        model.addAttribute("items", itemService.getAllItems());
        return "adminitems";
    }

    /**
     * Delete any item (admin privilege)
     */
    @GetMapping("/items/delete/{id}")
    public String deleteItem(@PathVariable Integer id,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedinuser");

        if (user.getRole() != Role.ADMIN) {
            return "redirect:/dashboard";
        }

        itemService.deleteItem(id);
        redirectAttributes.addFlashAttribute("message", "Item deleted successfully!");
        return "redirect:/admin/items";
    }
}

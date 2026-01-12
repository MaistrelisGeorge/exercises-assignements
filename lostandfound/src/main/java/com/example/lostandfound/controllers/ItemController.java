package com.example.lostandfound.controllers;

import com.example.lostandfound.entities.*;
import com.example.lostandfound.services.ItemService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

/**
 * gmaistrelis - Lost and Found - Controller for item CRUD operations
 */
@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * Show all items with search/filter options
     */
    @GetMapping("/all")
    public String showAllItems(ModelMap model) {
        model.addAttribute("items", itemService.getAllItems());
        model.addAttribute("categories", Category.values());
        model.addAttribute("statuses", ItemStatus.values());
        return "itemlist";
    }

    /**
     * Search items with filters
     */
    @GetMapping("/search")
    public String searchItems(@RequestParam(required = false) Category category,
                             @RequestParam(required = false) ItemStatus status,
                             @RequestParam(required = false) String location,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdate,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddate,
                             ModelMap model) {

        List<Item> items = itemService.searchItems(category, status, location, startdate, enddate);
        model.addAttribute("items", items);
        model.addAttribute("categories", Category.values());
        model.addAttribute("statuses", ItemStatus.values());
        return "itemlist";
    }

    /**
     * Show form to create new item
     */
    @GetMapping("/create")
    public String showCreateForm(ModelMap model) {
        model.addAttribute("item", new Item());
        model.addAttribute("categories", Category.values());
        model.addAttribute("statuses", ItemStatus.values());
        return "itemform";
    }

    /**
     * Process item creation
     */
    @PostMapping("/create")
    public String createItem(@Valid @ModelAttribute Item item,
                            BindingResult result,
                            HttpSession session,
                            ModelMap model,
                            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("categories", Category.values());
            model.addAttribute("statuses", ItemStatus.values());
            return "itemform";
        }

        User user = (User) session.getAttribute("loggedinuser");
        item.setReporter(user);
        itemService.saveItem(item);

        redirectAttributes.addFlashAttribute("message", "Item reported successfully!");
        return "redirect:/dashboard";
    }

    /**
     * Show item details with discussion board
     */
    @GetMapping("/view/{id}")
    public String viewItem(@PathVariable Integer id, ModelMap model) {
        Item item = itemService.findById(id);
        if (item == null) {
            return "redirect:/items/all";
        }
        model.addAttribute("item", item);
        return "itemdetail";
    }

    /**
     * Show form to edit item
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id,
                              HttpSession session,
                              ModelMap model) {
        Item item = itemService.findById(id);
        User user = (User) session.getAttribute("loggedinuser");

        // Check if user is owner or admin
        if (item == null || (!item.getReporter().getId().equals(user.getId()) && user.getRole() != Role.ADMIN)) {
            return "redirect:/items/all";
        }

        model.addAttribute("item", item);
        model.addAttribute("categories", Category.values());
        model.addAttribute("statuses", ItemStatus.values());
        return "itemedit";
    }

    /**
     * Process item update
     */
    @PostMapping("/edit/{id}")
    public String updateItem(@PathVariable Integer id,
                            @Valid @ModelAttribute Item item,
                            BindingResult result,
                            HttpSession session,
                            ModelMap model,
                            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("categories", Category.values());
            model.addAttribute("statuses", ItemStatus.values());
            return "itemedit";
        }

        Item existingItem = itemService.findById(id);
        User user = (User) session.getAttribute("loggedinuser");

        // Check permission
        if (!existingItem.getReporter().getId().equals(user.getId()) && user.getRole() != Role.ADMIN) {
            return "redirect:/items/all";
        }

        // Update fields
        existingItem.setItemname(item.getItemname());
        existingItem.setDescription(item.getDescription());
        existingItem.setCategory(item.getCategory());
        existingItem.setDatelostfound(item.getDatelostfound());
        existingItem.setLocation(item.getLocation());
        existingItem.setStatus(item.getStatus());
        existingItem.setContactinfo(item.getContactinfo());

        itemService.saveItem(existingItem);
        redirectAttributes.addFlashAttribute("message", "Item updated successfully!");
        return "redirect:/items/view/" + id;
    }

    /**
     * Delete item
     */
    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Integer id,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        Item item = itemService.findById(id);
        User user = (User) session.getAttribute("loggedinuser");

        // Check permission
        if (item == null || (!item.getReporter().getId().equals(user.getId()) && user.getRole() != Role.ADMIN)) {
            return "redirect:/items/all";
        }

        itemService.deleteItem(id);
        redirectAttributes.addFlashAttribute("message", "Item deleted successfully!");
        return "redirect:/dashboard";
    }
}

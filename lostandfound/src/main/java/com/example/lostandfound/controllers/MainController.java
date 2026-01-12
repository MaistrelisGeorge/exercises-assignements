package com.example.lostandfound.controllers;

import com.example.lostandfound.entities.Role;
import com.example.lostandfound.entities.User;
import com.example.lostandfound.services.ItemService;
import com.example.lostandfound.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * gmaistrelis - Lost and Found - Main controller for home, login and registration
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    /**
     * Show home page with recent items
     */
    @GetMapping("/")
    public String index(ModelMap model) {
        model.addAttribute("recentitems", itemService.getAllItems());
        return "index";
    }

    /**
     * Show login page
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Process login form
     */
    @PostMapping("/dologin")
    public String doLogin(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         HttpSession session,
                         ModelMap model) {
        User user = userService.authenticateUser(username, password);
        if (user == null) {
            model.addAttribute("message", "Invalid username or password");
            return "login";
        }
        session.setAttribute("loggedinuser", user);
        return "redirect:/dashboard";
    }

    /**
     * Show registration page
     */
    @GetMapping("/register")
    public String registerPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Process registration form
     */
    @PostMapping("/doregister")
    public String doRegister(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("confirmpassword") String confirmpassword,
                            @RequestParam("email") String email,
                            @RequestParam("fullname") String fullname,
                            ModelMap model,
                            RedirectAttributes redirectAttributes) {

        // Validate passwords match
        if (!password.equals(confirmpassword)) {
            model.addAttribute("message", "Passwords do not match");
            return "register";
        }

        // Check if username exists
        if (userService.findByUsername(username) != null) {
            model.addAttribute("message", "Username already exists");
            return "register";
        }

        // Check if email exists
        if (userService.findByEmail(email) != null) {
            model.addAttribute("message", "Email already exists");
            return "register";
        }

        // Register user with MEMBER role by default
        userService.registerUser(username, password, email, fullname, Role.MEMBER);
        redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");
        return "redirect:/login";
    }

    /**
     * Show user dashboard after login
     */
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, ModelMap model) {
        User user = (User) session.getAttribute("loggedinuser");
        model.addAttribute("items", itemService.findByReporter(user));
        return "dashboard";
    }

    /**
     * Logout user
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

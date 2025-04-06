package com.teamf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamf.entity.User;
import com.teamf.service.UserService;

import jakarta.servlet.http.HttpSession;

/**
 * Controller handling user registration, login, logout, and session-based access.
 */
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the registration form.
     *
     * @param model model used to bind the form
     * @return the register template
     */
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Handles the form submission for user registration.
     *
     * @param user  the user from the form
     * @param model model used to return errors if needed
     * @return redirects to login if successful, else back to registration
     */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.registerUser(user)) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Username already taken.");
            return "register";
        }
    }

    /**
     * Displays the login form.
     *
     * @return the login view
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    /**
     * Authenticates user credentials and stores session data.
     *
     * @param username the provided username
     * @param password the provided password
     * @param session  HTTP session for storing user
     * @param model    model for error messaging
     * @return redirects to homepage or back to login on failure
     */
    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        if (userService.validateUserLogin(username, password)) {
            session.setAttribute("user", userService.getUser(username));
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

    /**
     * Displays the logged-in user's account page.
     *
     * @param session HTTP session containing the user
     * @param model   model to populate with user data
     * @return account view or redirect to login if unauthenticated
     */
    @GetMapping("/account")
    public String accountPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        model.addAttribute("user", user);
        return "account";
    }

    /**
     * Logs the user out by invalidating the session.
     *
     * @param session the HTTP session to invalidate
     * @return redirect to login page
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

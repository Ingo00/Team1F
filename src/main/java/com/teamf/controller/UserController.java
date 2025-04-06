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
 * Controller handling user registration, login, session management, and account display.
 */
@Controller
public class UserController {

    private final UserService userService;

    /**
     * Constructs the controller with a user service.
     *
     * @param userService the user service
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the registration form.
     *
     * @param model the model for the form
     * @return the register template
     */
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Handles user registration.
     *
     * @param user the user submitted via form
     * @param model the model for errors
     * @return redirect to login on success or back to register on failure
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
     * @return the login template
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    /**
     * Authenticates a user.
     *
     * @param username the username
     * @param password the password
     * @param session the HTTP session
     * @param model the model for errors
     * @return redirect to account on success or back to login on failure
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
     * Displays the logged-in user's account.
     *
     * @param session the HTTP session
     * @param model the model containing user data
     * @return the account view or redirect to login if unauthenticated
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
     * @param session the HTTP session
     * @return redirect to login
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}

package com.spring.db.spscdemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // Show the login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // This will render login.html from templates
    }

    // Show the logout success page
    @GetMapping("/logout-success")
    public String showLogoutPage() {
        return "logout";  // This will render logout.html from templates
    }
}

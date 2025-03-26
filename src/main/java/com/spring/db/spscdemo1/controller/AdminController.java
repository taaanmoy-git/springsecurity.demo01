package com.spring.db.spscdemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";  // Returns `admin-dashboard.html` from `src/main/resources/templates/`
    }
}

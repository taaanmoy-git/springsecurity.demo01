package com.spring.db.spscdemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @GetMapping("/home")
    public String managerHome() {
        return "manager-home"; // ✅ Maps to `manager-home.html`
    }
}

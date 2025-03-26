package com.spring.db.spscdemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller  // Use @RestController for plain text responses
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public String userProfile() {
    	 return "user-profile"; // ✅ Maps to `user-profile.html`
    }
}

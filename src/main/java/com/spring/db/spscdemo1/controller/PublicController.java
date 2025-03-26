package com.spring.db.spscdemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/home")
    public String publicHome() {
        return "Welcome to the public home page!";
    }
}

package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "logout")
    public String getLogoutPage() {
        return "login";
    }

    @GetMapping("/")
    public String getPage() {
        return "admin";
    }
}

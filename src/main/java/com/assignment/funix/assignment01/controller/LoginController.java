package com.assignment.funix.assignment01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @RequestMapping("/login-page")
    public String login(@RequestParam(value = "message", defaultValue = "") String message, Model theModel){
        theModel.addAttribute("message", message);
        return "public/login";
    }
}

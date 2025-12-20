package com.doodzthreads.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("title", "Doodz Threads");
        model.addAttribute("tagline", "Members-only early access • Clean drops • Premium feel");
        return "welcome/index";
    }
}

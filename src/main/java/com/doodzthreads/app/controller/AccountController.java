package com.doodzthreads.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/account")
    public String dashboard(Model model, Authentication auth) {
        model.addAttribute("title", "Account • Doodz Threads");

        boolean loggedIn = auth != null && auth.isAuthenticated()
                && !"anonymousUser".equals(String.valueOf(auth.getPrincipal()));

        model.addAttribute("loggedIn", loggedIn);

        // Fallback until we wire real profile fields
        String firstName = "Friend";
        if (loggedIn && auth.getName() != null && auth.getName().contains("@")) {
            firstName = auth.getName().split("@")[0];
        }
        model.addAttribute("firstName", firstName);

        return "account/dashboard";
    }
}

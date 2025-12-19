package com.doodzthreads.app.controller;

import com.doodzthreads.app.controller.dto.SignupRequest;
import com.doodzthreads.app.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("form", new SignupRequest());
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@Valid @ModelAttribute("form") SignupRequest form,
                               BindingResult binding,
                               Model model) {

        System.out.println(">>> POST /signup hit with email=" + form.getEmail());

        if (binding.hasErrors()) {
            System.out.println(">>> validation errors: " + binding.getAllErrors());
            return "auth/signup";
        }

        try {
            authService.signup(form);
            System.out.println(">>> signup saved OK for " + form.getEmail());
        } catch (IllegalArgumentException e) {
            model.addAttribute("signupError", e.getMessage());
            System.out.println(">>> signup failed: " + e.getMessage());
            return "auth/signup";
        }

        return "redirect:/login?created";
    }

}

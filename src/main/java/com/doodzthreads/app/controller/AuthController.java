package com.doodzthreads.app.controller;

import com.doodzthreads.app.controller.dto.SignupRequest;
import com.doodzthreads.app.security.DbUserDetailsService;
import com.doodzthreads.app.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AuthService authService;
    private final DbUserDetailsService userDetailsService;

    public AuthController(AuthService authService, DbUserDetailsService userDetailsService) {
        this.authService = authService;
        this.userDetailsService = userDetailsService;
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

        // Auto-login after signup
        var userDetails = userDetailsService.loadUserByUsername(form.getEmail());
        var authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);

        return "redirect:/welcome";
    }
}

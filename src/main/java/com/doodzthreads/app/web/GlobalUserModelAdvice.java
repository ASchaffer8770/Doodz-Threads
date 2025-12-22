package com.doodzthreads.app.web;

import com.doodzthreads.app.repository.UserRepository;
import com.doodzthreads.app.util.NameUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(annotations = Controller.class)
public class GlobalUserModelAdvice {

    private final UserRepository userRepository;

    public GlobalUserModelAdvice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public void addGlobalUser(Model model, Authentication authentication) {

        boolean loggedIn = authentication != null
                && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated();

        model.addAttribute("loggedIn", loggedIn);

        if (!loggedIn) {
            model.addAttribute("firstName", "");
            model.addAttribute("isAdmin", false);
            return;
        }

        boolean isAdmin = authentication.getAuthorities() != null
                && authentication.getAuthorities().stream()
                .anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()));

        model.addAttribute("isAdmin", isAdmin);

        String email = authentication.getName(); // username == email
        userRepository.findByEmail(email).ifPresentOrElse(
                user -> model.addAttribute("firstName", NameUtils.firstName(user.getFullName())),
                () -> model.addAttribute("firstName", "")
        );
    }
}
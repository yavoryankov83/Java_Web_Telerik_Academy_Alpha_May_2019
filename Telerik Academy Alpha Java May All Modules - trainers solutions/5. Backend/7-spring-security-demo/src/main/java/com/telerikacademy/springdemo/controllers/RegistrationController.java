package com.telerikacademy.springdemo.controllers;

import com.telerikacademy.springdemo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {
    private UserDetailsManager userDetailsManager;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Username/password can't be empty!");
            return "register";
        }

        if(userDetailsManager.userExists(user.getUsername())) {
            model.addAttribute("error", "User with same username already exists!");
            return "register";
        }

        if(!user.getPassword().equals(user.getPasswordConfirmation())) {
            model.addAttribute("error", "Password doesn't match!");
            return "register";
        }

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        org.springframework.security.core.userdetails.User newUser =
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(), passwordEncoder.encode(user.getPassword()), authorities);
        userDetailsManager.createUser(newUser);
        return "register-confirmation";
    }

    @GetMapping("/register-confirmation")
    public String registerConfirmation() {
        return "register-confirmation";
    }
}

package com.webApp.blog.controller;

import com.webApp.blog.model.User;
import com.webApp.blog.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {


   /* @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // return the login.jsp page
    }*/
    /*  @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("VIEWER"); // or "POSTER" based on logic
        userRepo.save(user);
        return "redirect:/login";*/
    //}

}

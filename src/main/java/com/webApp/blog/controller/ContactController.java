package com.webApp.blog.controller;

import com.webApp.blog.model.Contact;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {

    @GetMapping("/support")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/submitContact")
    public String submitContact(@Valid @ModelAttribute("contact") Contact contact,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "contact";
        }
        model.addAttribute("message", "Thank you for contacting us!");
        return "contact";
    }
}

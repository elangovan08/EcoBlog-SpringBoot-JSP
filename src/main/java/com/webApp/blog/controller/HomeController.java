package com.webApp.blog.controller;

import com.webApp.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private PostRepository postRepo;

  /*  @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("ecoDescription", "🌿 EcoBlog – An Eco-Conscious Blog Platform: Share your thoughts, suggestions, and protests about tech, pollution, and social awareness for a better world.");
        model.addAttribute("listPosts", postRepo.findAll());
        return "home";
    }
*/
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}

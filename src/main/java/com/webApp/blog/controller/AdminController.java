package com.webApp.blog.controller;

import com.webApp.blog.repository.CommentRepository;
import com.webApp.blog.repository.PostRepository;
import com.webApp.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private CommentRepository commentRepo;

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("userCount", userRepo.count());
        model.addAttribute("postCount", postRepo.count());
        model.addAttribute("commentCount", commentRepo.count());
        model.addAttribute("recentUsers", userRepo.findTop5ByOrderByIdDesc());
        return "admin-dashboard";
    }
}

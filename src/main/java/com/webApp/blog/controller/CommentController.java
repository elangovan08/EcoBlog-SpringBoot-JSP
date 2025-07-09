package com.webApp.blog.controller;

import com.webApp.blog.model.Comment;
import com.webApp.blog.model.Post;
import com.webApp.blog.model.User;
import com.webApp.blog.repository.CommentRepository;
import com.webApp.blog.repository.PostRepository;
import com.webApp.blog.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;
   @PostMapping("/add/{postId}")
   public String addComment(@PathVariable Long postId,
                            @ModelAttribute("comment") @Valid Comment comment,
                            BindingResult result,
                            HttpSession session,
                            Model model) {

       if (result.hasErrors()) {
           return "redirect:/posts/" + postId + "?error=true";
       }

       // Get the post from repository
       Post post = postRepo.findById(postId)
               .orElseThrow(() -> new IllegalArgumentException("Invalid Post ID: " + postId));
       comment.setPost(post);

       // Simulate logged-in user retrieval (later to be replaced with Spring Security)
       String username = (String) session.getAttribute("username");

       // Safely extract User from Optional
       User user = userRepo.findByUsername(username)
               .orElseThrow(() -> new IllegalArgumentException("Invalid user: " + username));

       comment.setUser(user);
       commentRepo.save(comment);

       return "redirect:/posts/" + postId;
   }

}

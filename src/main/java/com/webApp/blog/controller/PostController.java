package com.webApp.blog.controller;

import com.webApp.blog.model.Comment;
import com.webApp.blog.model.Contact;
import com.webApp.blog.model.Post;
import com.webApp.blog.model.User;
import com.webApp.blog.repository.CommentRepository;
import com.webApp.blog.repository.PostRepository;
import com.webApp.blog.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/posts")
    public String viewPosts(Model model) {
        List<Post> posts = postRepo.findAll();
        System.out.println("Post count: " + posts.size()); // debug
        for (Post p : posts) {
            System.out.println("Post: " + p.getTitle());
        }
        model.addAttribute("listPosts", posts);
        return "post-list";
    }

    // Show form to add new post
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("post", new Post());
        return "add-post";
    }

 /*   @PostMapping("/save")
    public String savePost(@ModelAttribute("post") Post post) {
        postRepo.save(post);
        return "redirect:/posts";
    }*/
 /*@PostMapping("/save")
 @Transactional
 public String savePost(@ModelAttribute("post") @Valid Post post,
                        BindingResult result,
                        @RequestParam("image") MultipartFile file,
                        Model model) {

     if (result.hasErrors()) {
         model.addAttribute("error", "Validation error");
         return "add-post";
     }

     try {
         // Dummy logged-in user
         User user = userRepo.findById(1L)
                 .orElseThrow(() -> new RuntimeException("User not found"));

         Post postToSave = (post.getId() != null)
                 ? postRepo.findById(post.getId()).orElseThrow(() -> new RuntimeException("Post not found"))
                 : new Post();

         postToSave.setTitle(post.getTitle());
         postToSave.setContent(post.getContent());
         postToSave.setAuthor(user); // always set

         // ✅ IMAGE UPLOAD (WAR-SAFE)
         if (!file.isEmpty()) {
             // 1. Upload location outside WAR
             String uploadDir = System.getProperty("user.dir") + "/uploads/post-images";
             File imageDir = new File(uploadDir);

             // 2. Create folder if missing
             if (!imageDir.exists()) {
                 boolean created = imageDir.mkdirs();
                 if (created) {
                     System.out.println("✅ Folder created: " + uploadDir);
                 } else {
                     throw new IOException("❌ Could not create image directory");
                 }
             }

             // 3. Save file with unique name
             String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
             File saveFile = new File(imageDir, filename);
             file.transferTo(saveFile);
             postToSave.setImage(filename);

             System.out.println("✅ Image saved: " + saveFile.getAbsolutePath());
         }

         postRepo.save(postToSave);
         return "redirect:/posts";

     } catch (Exception e) {
         e.printStackTrace();
         model.addAttribute("error", "Error saving post or uploading image.");
         return "add-post";
     }
 }*/
 @PostMapping("/save")
 @Transactional
 public String savePost(@ModelAttribute("post") @Valid Post post,
                        BindingResult result,
                        Model model) {
     if (result.hasErrors()) {
         model.addAttribute("error", "Validation error");
         return "add-post";
     }

     try {
         User user = userRepo.findById(1L)
                 .orElseThrow(() -> new RuntimeException("User not found"));

         Post postToSave = (post.getId() != null)
                 ? postRepo.findById(post.getId()).orElseThrow(() -> new RuntimeException("Post not found"))
                 : new Post();

         postToSave.setTitle(post.getTitle());
         postToSave.setContent(post.getContent());
         postToSave.setAuthor(user);

         // No image logic anymore
         postRepo.save(postToSave);
         return "redirect:/posts";

     } catch (Exception e) {
         e.printStackTrace();
         model.addAttribute("error", "Error saving post.");
         return "add-post";
     }
 }

    // Edit post
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Post post = postRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + id));
        model.addAttribute("post", post);
        return "add-post";
    }

    // View post details + comments
    @GetMapping("/posts/{id}")
    public String viewPostDetails(@PathVariable("id") Long id, Model model) {
        Post post = postRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + id));
        List<Comment> comments = commentRepo.findByPostId(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        return "post-details";
    }

    // Add comment
    @PostMapping("/posts/{id}/comment")
    public String addComment(@PathVariable("id") Long postId,
                             @ModelAttribute("comment") Comment comment,
                             Principal principal) {
      /*  User user = userRepo.findByUsername(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + principal.getName()));*/
        User user = userRepo.findById(1L).orElseThrow(); // test with dummy user

        comment.setUser(user);
        comment.setPost(postRepo.findById(postId).orElseThrow());
        commentRepo.save(comment);
        return "redirect:/posts/" + postId;
    }

  @GetMapping("/like/{id}")
  public String likePost(@PathVariable("id") Long id) {
      Post post = postRepo.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + id));

      post.setLikeCount(post.getLikeCount() + 1); // Increase like count
      postRepo.save(post);

      return "redirect:/posts/" + id;
  }

    // Bookmark post
    @GetMapping("/bookmark/{id}")
    public String bookmarkPost(@PathVariable("id") Long id, Principal principal) {
        /*User user = userRepo.findByUsername(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + principal.getName()));*/
        User user = userRepo.findById(1L).orElseThrow(); // test with dummy user

        Post post = postRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + id));
        user.getBookmarkedPosts().add(post);
        userRepo.save(user);
        return "redirect:/posts/" + id;
    }

    // Delete post
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postRepo.deleteById(id);
        return "redirect:/posts";
    }

    // Search posts
    @GetMapping("/search")
    public String searchPosts(@RequestParam("keyword") String keyword, Model model) {
        List<Post> result = postRepo.findByTitleContainingIgnoreCase(keyword);
        model.addAttribute("listPosts", result);
        model.addAttribute("searchKeyword", keyword);
        return "post-list";
    }
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("ecoDescription", "🌿 EcoBlog – An Eco-Conscious Blog Platform: Share your thoughts, suggestions, and protests about tech, pollution, and social awareness for a better world.");
        model.addAttribute("listPosts", postRepo.findAll());
        return "home";
    }

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(@Valid @ModelAttribute("contact") Contact contact,
                                BindingResult result, Model model) {
        if (result.hasErrors()) return "contact";
        model.addAttribute("message", "Your message has been submitted successfully!");
        model.addAttribute("contact", new Contact());
        return "contact";
    }
    @Autowired
    private UserRepository userRepo2;

    /*@Autowired
    private PasswordEncoder passwordEncoder;
*/
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute("user") @Valid User user,
                                BindingResult result,
                                Model model) {

        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            result.rejectValue("username", "error.user", "Username already taken");
        }

        if (result.hasErrors()) {
            return "signup";
        }

        //  user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("POSTER");
        }

        userRepo2.save(user);
        //   return "redirect:/login?signupSuccess";
        return "redirect:/posts";  // or "/home"
    }

    @GetMapping("/debug-posts")
    @ResponseBody
    public List<Post> debugPosts() {
        return postRepo.findAll();
    }

}

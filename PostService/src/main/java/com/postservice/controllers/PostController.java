package com.postservice.controllers;

import com.postservice.models.Post;
import com.postservice.services.PostService;
import com.postservice.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Arrays;
import static com.postservice.utils.JwtUtils.requireRole;

@RestController
@RequestMapping("/api/posts")

public class PostController {


    @Autowired
    private PostService postService;
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }


    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id, HttpServletRequest request) {
        requireRole(request, jwtUtils, List.of("ADMIN"));
        postService.deleteById(Math.toIntExact(id));
    }


    @PostMapping("/")
    public Post createPost(@RequestBody Post post, HttpServletRequest request) {
        requireRole(request, jwtUtils, Arrays.asList("ADMIN", "BLOGGER"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        post.setAuthor(username);
        return postService.save(post);
    }

}

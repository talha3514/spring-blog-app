package com.example.blog.controller;

import com.example.blog.domain.Category;
import com.example.blog.domain.Post;
import com.example.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        Optional<Post> Post = postService.getPostById(id);
        return Post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Post createPost(@RequestBody Post Post) {
        return postService.createPost(Post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post Post) {
        return postService.updatePost(id, Post);
    }
}

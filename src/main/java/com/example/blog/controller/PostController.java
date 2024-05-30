package com.example.blog.controller;

import com.example.blog.domain.Category;
import com.example.blog.domain.Post;
import com.example.blog.service.PostCategoryService;
import com.example.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final PostCategoryService postCategoryService;

    public PostController(PostService postService, PostCategoryService postCategoryService) {
        this.postService = postService;
        this.postCategoryService = postCategoryService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> Post = postService.getPostById(id);
        return Post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Post createPost(@RequestBody Post Post) {
        return postService.createPost(Post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories/{id}")
    public List<Category> getCategoriesByPostId(@PathVariable Long id) {
        return postCategoryService.getAllCategoryByPostId(id);
    }
}

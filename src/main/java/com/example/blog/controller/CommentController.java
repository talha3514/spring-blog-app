package com.example.blog.controller;

import com.example.blog.domain.Comment;
import com.example.blog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;
    
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllCategories() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long id) {
        Optional<Comment> Comment = commentService.getCommentById(id);
        return Comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment Comment) {
        return commentService.createComment(Comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public Comment updateComment(@PathVariable("id") Long id, @RequestBody Comment Comment) {
        return commentService.updateComment(id, Comment);
    }
}

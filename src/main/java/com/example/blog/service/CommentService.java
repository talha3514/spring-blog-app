package com.example.blog.service;

import com.example.blog.domain.Comment;
import com.example.blog.repository.CommentRepository;
import com.example.blog.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    private final CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        List<com.example.blog.entity.Comment> comments = commentRepository.findAll();

        return comments.stream()
                .map(x -> ModelUtil.modelMapper.map(x, com.example.blog.domain.Comment.class))
                .toList();
    }

    public Optional<Comment> getCommentById(Long id) {
        Optional<com.example.blog.entity.Comment> comment = commentRepository.findById(id);
        return Optional.ofNullable(ModelUtil.modelMapper.map(comment, com.example.blog.domain.Comment.class));
    }

    public com.example.blog.domain.Comment createComment(com.example.blog.domain.Comment comment) {
        com.example.blog.entity.Comment commentEntity = ModelUtil.modelMapper.map(comment, com.example.blog.entity.Comment.class);
        commentEntity = commentRepository.save(commentEntity);

        return ModelUtil.modelMapper.map(commentEntity, com.example.blog.domain.Comment.class);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

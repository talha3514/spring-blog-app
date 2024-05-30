package com.example.blog.service;

import com.example.blog.domain.Post;
import com.example.blog.repository.PostRepository;
import com.example.blog.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        List<com.example.blog.entity.Post> Posts = postRepository.findAll();

        return Posts.stream()
                .map(x -> ModelUtil.modelMapper.map(x, com.example.blog.domain.Post.class))
                .toList();
    }

    public Optional<Post> getPostById(Long id) {
        Optional<com.example.blog.entity.Post> Post = postRepository.findById(id);
        return Optional.ofNullable(ModelUtil.modelMapper.map(Post, com.example.blog.domain.Post.class));
    }

    public com.example.blog.domain.Post createPost(com.example.blog.domain.Post Post) {
        com.example.blog.entity.Post PostEntity = ModelUtil.modelMapper.map(Post, com.example.blog.entity.Post.class);
        PostEntity = postRepository.save(PostEntity);

        return ModelUtil.modelMapper.map(PostEntity, com.example.blog.domain.Post.class);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}

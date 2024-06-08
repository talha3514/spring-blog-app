package com.example.blog.service;

import com.example.blog.domain.Post;
import com.example.blog.entity.Category;
import com.example.blog.entity.User;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    private final PostRepository postRepository;
    private final UserRepository userRepository;

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
        //com.example.blog.entity.Post PostEntity = ModelUtil.modelMapper.map(Post, com.example.blog.entity.Post.class);
        Optional<User> user = userRepository.findById(Post.authorId);
        if(user.isEmpty()){
            return new com.example.blog.domain.Post();
        }

        com.example.blog.entity.Post postEntity = new com.example.blog.entity.Post();
        postEntity.setTitle(Post.getTitle());
        postEntity.setContent(Post.getContent());
        postEntity.setAuthor(user.get());
        user.get().setPost(postEntity);

        postRepository.save(postEntity);

        return ModelUtil.modelMapper.map(postEntity, com.example.blog.domain.Post.class);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public com.example.blog.domain.Post updatePost(Long id, com.example.blog.domain.Post Post) {
        Optional<com.example.blog.entity.Post> post = postRepository.findById(id);
        if(post.isEmpty()){
            return null;
        }

        List<Category> categories = categoryRepository.findByIdIn(Post.getCategoryId());
        Set<Category> categorySet = new HashSet<>(categories);
        categorySet.addAll(categories);

        post.get().setTitle(Post.getTitle());
        post.get().setContent(Post.getContent());
        post.get().setCategories(categorySet);
        post.get().setAuthor(post.get().getAuthor());
        postRepository.save(post.get());

        return ModelUtil.modelMapper.map(post.get(), com.example.blog.domain.Post.class);
    }
}

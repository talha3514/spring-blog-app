package com.example.blog.service;

import com.example.blog.domain.Category;
import com.example.blog.domain.Post;
import com.example.blog.entity.PostCategory;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.repository.PostCategoryRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCategoryService {
    @Autowired
    public PostCategoryService(PostCategoryRepository postCategoryRepository, PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postCategoryRepository = postCategoryRepository;
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    private final PostCategoryRepository postCategoryRepository;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public List<Post> getAllPostByCategoryId(Long categoryId) {
        List<PostCategory> postCategories = postCategoryRepository.GetAllPostByCategoryId(categoryId);
        List<com.example.blog.entity.Post> posts = postRepository.findByIdIn(postCategories.stream().map(x -> x.postId).toList());

        return posts.stream()
                .map(x -> ModelUtil.modelMapper.map(x, com.example.blog.domain.Post.class))
                .toList();
    }

    public List<Category> getAllCategoryByPostId(Long postId) {
        List<PostCategory> postCategories = postCategoryRepository.GetAllCategoryByPostId(postId);
        List<com.example.blog.entity.Category> categories = categoryRepository.findByIdIn(postCategories.stream().map(x -> x.categoryId).toList());

        return categories.stream()
                .map(x -> ModelUtil.modelMapper.map(x, com.example.blog.domain.Category.class))
                .toList();
    }
}

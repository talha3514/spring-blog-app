package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.blog.entity.PostCategory;

import java.util.List;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {
    public List<PostCategory> GetAllPostByCategoryId(Long categoryId);
    public List<PostCategory> GetAllCategoryByPostId(Long postId);
}

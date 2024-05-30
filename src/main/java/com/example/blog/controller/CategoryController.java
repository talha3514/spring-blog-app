package com.example.blog.controller;

import com.example.blog.domain.Category;
import com.example.blog.domain.Post;
import com.example.blog.service.CategoryService;
import com.example.blog.service.PostCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final PostCategoryService postCategoryService;

    public CategoryController(CategoryService categoryService, PostCategoryService postCategoryService) {
        this.categoryService = categoryService;
        this.postCategoryService = postCategoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> Category = categoryService.getCategoryById(id);
        return Category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Category createCategory(@RequestBody Category Category) {
        return categoryService.createCategory(Category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts/{id}")
    public List<Post> getPostsByCategoryId(@PathVariable Long id) {
        return postCategoryService.getAllPostByCategoryId(id);
    }
}

package com.example.blog.controller;

import com.example.blog.domain.Category;
import com.example.blog.domain.Post;
import com.example.blog.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        Optional<Category> Category = categoryService.getCategoryById(id);
        return Category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Category createCategory(@RequestBody Category Category) {
        return categoryService.createCategory(Category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public Category updateCategory(@PathVariable("id") Long id, @RequestBody Category Category) {
        return categoryService.updateCategory(id, Category);
    }
}

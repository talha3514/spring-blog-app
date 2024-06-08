package com.example.blog.service;

import com.example.blog.domain.Category;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository  = categoryRepository;
    }

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        List<com.example.blog.entity.Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(x -> ModelUtil.modelMapper.map(x, com.example.blog.domain.Category.class))
                .toList();
    }

    public Optional<Category> getCategoryById(Long id) {
        Optional<com.example.blog.entity.Category> category = categoryRepository.findById(id);

        return Optional.ofNullable(ModelUtil.modelMapper.map(category, com.example.blog.domain.Category.class));
    }

    public com.example.blog.domain.Category createCategory(com.example.blog.domain.Category category) {
        com.example.blog.entity.Category categoryEntity = ModelUtil.modelMapper.map(category, com.example.blog.entity.Category.class);
        categoryEntity = categoryRepository.save(categoryEntity);

        return ModelUtil.modelMapper.map(categoryEntity, com.example.blog.domain.Category.class);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(Long id, com.example.blog.domain.Category category) {
        Optional<com.example.blog.entity.Category> categoryEntity = categoryRepository.findById(id);
        if (categoryEntity.isEmpty()) {
            return null;
        }

        categoryEntity.get().setTitle(category.getTitle());
        categoryEntity.get().setContent(category.getContent());
        categoryRepository.save(categoryEntity.get());
        return ModelUtil.modelMapper.map(categoryEntity, com.example.blog.domain.Category.class);
    }
}

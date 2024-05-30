package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.blog.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}

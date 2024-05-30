package com.example.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "category")
public class Category extends AbstractEntity<Long> {
    private String title;
    private String content;
}

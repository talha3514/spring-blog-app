package com.example.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post extends AbstractEntity<Long> {
    private Long authorId;
    private String title;
    private String content;
}

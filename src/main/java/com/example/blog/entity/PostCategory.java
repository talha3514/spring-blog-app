package com.example.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "post_category")
public class PostCategory extends AbstractEntity<Long>{
    public Long postId;
    public Long categoryId;
}

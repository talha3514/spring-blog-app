package com.example.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends AbstractEntity<Long> {
    private Long postId;
    private String title;
    private String content;
    private Long userId;
}

package com.example.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends AbstractEntity<Long> {
    public String title;
    public String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    public Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
}

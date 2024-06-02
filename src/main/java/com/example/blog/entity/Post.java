package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post extends AbstractEntity<Long> {
    public String title;
    public String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    public User author;

    @OneToMany(mappedBy = "post")
    public Set<Comment> comments;

    @ManyToMany(mappedBy = "posts")
    public Set<Category> categories;
}

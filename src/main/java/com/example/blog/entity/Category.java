package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "category")
public class Category extends AbstractEntity<Long> {
    public String title;
    public String content;

    @ManyToMany
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    public Set<Post> posts;
}

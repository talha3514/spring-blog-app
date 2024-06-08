package com.example.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Content {
    public Long authorId;
    public Set<Long> categoryId;
}

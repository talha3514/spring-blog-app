package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "user")
public class User extends AbstractEntity<Long> {
    public String username;
    public String firstname;
    public String lastname;
    public String password;

    @ManyToOne
    @JoinColumn(name = "post_id")
    public Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserInfo userInfo;

    @OneToMany(mappedBy = "user")
    public Set<Comment> comments;
}

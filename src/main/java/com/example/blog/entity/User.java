package com.example.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "user")
public class User extends AbstractEntity<Long> {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
}

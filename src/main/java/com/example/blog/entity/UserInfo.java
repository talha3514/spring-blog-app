package com.example.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "user_info")
public class UserInfo extends AbstractEntity<Long> {
    private String city;
    private String phone;
    private String email;
    private Long userid;
}

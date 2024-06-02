package com.example.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "user_info")
public class UserInfo extends AbstractEntity<Long> {
    public String city;
    public String phone;
    public String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    public User user;
}

package com.example.blog.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseDomain {
    @CreationTimestamp
    public LocalDateTime created;

    @UpdateTimestamp
    public LocalDateTime updated;
}

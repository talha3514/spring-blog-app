package com.example.blog.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

//@NoArgsConstructor
//@Data
//@Entity
@MappedSuperclass
public class AbstractEntity<ID> {

    @Id
    //@Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public ID id;

    @JsonIgnore
    @Version
    public Integer version;

    @JsonIgnore
    @CreationTimestamp
    public LocalDateTime created;

    @JsonIgnore
    @UpdateTimestamp
    public LocalDateTime updated;
}

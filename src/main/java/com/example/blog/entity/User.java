package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
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

    //@OneToOne(
    //        fetch = FetchType.LAZY,
    //        orphanRemoval = true,
    //        cascade = CascadeType.ALL
    //)
    //@JoinColumn(name = "user", referencedColumnName = "ID")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    public UserInfo userInfo;

    @OneToMany(mappedBy = "user")
    public Set<Comment> comments;
}

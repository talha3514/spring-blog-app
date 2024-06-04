package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "user_info")
public class UserInfo extends AbstractEntity<Long> {
    public String city;
    public String phone;
    public String email;


    //@OneToOne(fetch = FetchType.LAZY,
    //        orphanRemoval = true,
    //        cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id", referencedColumnName = "ID")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}

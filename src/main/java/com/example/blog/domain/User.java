package com.example.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public String username;
    public String firstname;
    public String lastname;
    public String password;

    public UserInfo userInfo;
}

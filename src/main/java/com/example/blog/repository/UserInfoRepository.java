package com.example.blog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.blog.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    public UserInfo findByUserId(Long userId);
    public UserInfo deleteByUserId(Long userId);
}

package com.example.blog.service;

import com.example.blog.domain.UserInfo;
import com.example.blog.repository.UserInfoRepository;
import com.example.blog.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService {
    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    private final UserInfoRepository userInfoRepository;

    public List<UserInfo> getAllUserInfos() {
        List<com.example.blog.entity.UserInfo> UserInfos = userInfoRepository.findAll();

        return UserInfos.stream()
                .map(x -> ModelUtil.modelMapper.map(x, com.example.blog.domain.UserInfo.class))
                .toList();
    }

    public Optional<UserInfo> getUserInfoByUserId(Long userId) {
        com.example.blog.entity.UserInfo userInfo = userInfoRepository.findByUserId(userId);

        return Optional.ofNullable(ModelUtil.modelMapper.map(userInfo, com.example.blog.domain.UserInfo.class));
    }

    public com.example.blog.domain.UserInfo createUserInfo(com.example.blog.domain.UserInfo UserInfo) {
        com.example.blog.entity.UserInfo UserInfoEntity = ModelUtil.modelMapper.map(UserInfo, com.example.blog.entity.UserInfo.class);
        UserInfoEntity = userInfoRepository.save(UserInfoEntity);

        return ModelUtil.modelMapper.map(UserInfoEntity, com.example.blog.domain.UserInfo.class);
    }

    public void deleteUserInfo(Long id) {
        userInfoRepository.deleteById(id);
    }
}

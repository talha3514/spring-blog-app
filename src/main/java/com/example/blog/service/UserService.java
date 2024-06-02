package com.example.blog.service;

import com.example.blog.domain.UserDetails;
import com.example.blog.entity.User;
import com.example.blog.entity.UserInfo;
import com.example.blog.repository.UserInfoRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserService(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    public List<com.example.blog.domain.User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(x -> {
                    return ModelUtil.modelMapper.map(x, com.example.blog.domain.User.class);
                })
                .toList();
    }

    public Optional<com.example.blog.domain.User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return Optional.ofNullable(ModelUtil.modelMapper.map(user, com.example.blog.domain.User.class));
    }

    public com.example.blog.domain.User createUser(UserDetails userDetails) {
        User userEntity = new User();
        userEntity.username = userDetails.getUsername();
        userEntity.firstname = userDetails.getFirstname();
        userEntity.lastname = userDetails.getLastname();
        userEntity.password = userDetails.getPassword();

        UserInfo userInfoEntity = new UserInfo();
        userInfoEntity.city = userDetails.getCity();
        userInfoEntity.phone = userDetails.getPhone();
        userInfoEntity.email = userDetails.getEmail();

        userEntity = userRepository.save(userEntity);
        userInfoEntity.user = userEntity;
        userInfoRepository.save(userInfoEntity);

        return ModelUtil.modelMapper.map(userEntity, com.example.blog.domain.User.class);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        userInfoRepository.deleteByUserId(id);
    }
}

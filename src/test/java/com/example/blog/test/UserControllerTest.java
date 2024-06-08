package com.example.blog.test;

import com.example.blog.controller.UserController;
import com.example.blog.domain.UserDetails;
import com.example.blog.entity.User;
import com.example.blog.service.UserService;
import com.example.blog.util.ModelUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        user1.setLastname("admin");
        user1.setId(1L);

        user2 = new User();
        user2.setUsername("user");
        user2.setPassword("password");
        user2.setLastname("user");
        user1.setId(2L);

    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> allUsersEntity = Arrays.asList(user1, user2);

        List<com.example.blog.domain.User> allUsers = allUsersEntity.stream()
                .map(x -> ModelUtil.modelMapper.map(x, com.example.blog.domain.User.class))
                .toList();

        given(userService.getAllUsers()).willReturn(allUsers);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value(user1.getUsername()))
                .andExpect(jsonPath("$[1].username").value(user2.getUsername()))
                .andDo(print());
    }

    @Test
    public void testGetUserById() throws Exception {
        Optional<com.example.blog.domain.User> userDomain =
                Optional.ofNullable(ModelUtil.modelMapper.map(user1, com.example.blog.domain.User.class));
        given(userService.getUserById(user1.getId())).willReturn(userDomain);

        Long id = user1.getId();

        mockMvc.perform(get("/api/users/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user1.getUsername()))
                .andDo(print());
    }

    @Test
    public void testCreateUser() throws Exception {
        UserDetails userDetails = ModelUtil.modelMapper.map(user1, UserDetails.class);
        com.example.blog.domain.User userDomain = ModelUtil.modelMapper.map(user1, com.example.blog.domain.User.class);
        given(userService.createUser(userDetails)).willReturn(userDomain);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user1.getUsername()))
                .andDo(print());
    }


    @Test
    public void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(user1.getId());

        mockMvc.perform(delete("/api/users/{id}", user1.getId()))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}

package com.example.blog.test;

import com.example.blog.controller.PostController;
import com.example.blog.entity.Post;
import com.example.blog.entity.Post;
import com.example.blog.service.PostService;
import com.example.blog.service.PostService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.example.blog.util.ModelUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTest {
    @MockBean
    private PostService PostService;

    @Autowired
    private MockMvc mockMvc;

    private Post post1;
    private Post post2;
    @Autowired
    private PostService postService;

    @BeforeEach
    public void setUp() {
        post1 = new Post();
        post1.setId(1L);
        post1.setTitle("cat1");
        post1.setContent("cat1");

        post2 = new Post();
        post2.setId(2L);
        post2.setTitle("cat2");
        post2.setContent("cat2");
    }

    @Test
    public void testGetAllposts() throws Exception {
        List<Post> allPostEntity = Arrays.asList(post1, post2);

        List<com.example.blog.domain.Post> allPost = allPostEntity.stream()
                .map(x -> ModelUtil.modelMapper.map(x, com.example.blog.domain.Post.class))
                .toList();

        given(postService.getAllPosts()).willReturn(allPost);

        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(post1.getTitle()))
                .andExpect(jsonPath("$[1].title").value(post2.getTitle()))
                .andDo(print());
    }

    @Test
    public void testGetPostById() throws Exception {
        Optional<com.example.blog.domain.Post> postDomain =
                Optional.ofNullable(ModelUtil.modelMapper.map(post1, com.example.blog.domain.Post.class));
        given(PostService.getPostById(post1.getId())).willReturn(postDomain);

        Long id = post1.getId();

        mockMvc.perform(get("/api/posts/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(post1.getTitle()))
                .andDo(print());
    }

    @Test
    public void testCreatePost() throws Exception {
        com.example.blog.domain.Post postDomain = ModelUtil.modelMapper.map(post1, com.example.blog.domain.Post.class);
        given(PostService.createPost(postDomain)).willReturn(postDomain);

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postDomain)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(post1.getTitle()))
                .andDo(print());
    }

    @Test
    public void testDeletePost() throws Exception {
        doNothing().when(PostService).deletePost(post1.getId());

        mockMvc.perform(delete("/api/posts/{id}", post1.getId()))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}

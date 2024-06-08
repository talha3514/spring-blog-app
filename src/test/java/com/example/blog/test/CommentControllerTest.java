package com.example.blog.test;

import com.example.blog.controller.CommentController;
import com.example.blog.entity.Comment;
import com.example.blog.service.CommentService;
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

@WebMvcTest(CommentController.class)
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    private Comment comment1;
    private Comment comment2;

    @BeforeEach
    public void setUp() {
        comment1 = new Comment();
        comment1.setId(1L);
        comment1.setTitle("comment1");
        comment1.setContent("comment1");

        comment2 = new Comment();
        comment2.setId(2L);
        comment2.setTitle("comment2");
        comment2.setContent("comment2");
    }

    @Test
    public void testGetAllComments() throws Exception {
        List<Comment> allCommentsEntity = Arrays.asList(comment1, comment2);

        List<com.example.blog.domain.Comment> allComment = allCommentsEntity.stream()
                .map(x -> ModelUtil.modelMapper.map(x, com.example.blog.domain.Comment.class))
                .toList();

        given(commentService.getAllComments()).willReturn(allComment);

        mockMvc.perform(get("/api/comment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(comment1.getTitle()))
                .andExpect(jsonPath("$[1].title").value(comment2.getTitle()))
                .andDo(print());
    }

    @Test
    public void testGetCommentById() throws Exception {
        Optional<com.example.blog.domain.Comment> commentDomain =
                Optional.ofNullable(ModelUtil.modelMapper.map(comment1, com.example.blog.domain.Comment.class));
        given(commentService.getCommentById(comment1.getId())).willReturn(commentDomain);

        Long id = comment1.getId();

        mockMvc.perform(get("/api/comment/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(comment1.getTitle()))
                .andDo(print());
    }

    @Test
    public void testCreateComment() throws Exception {
        com.example.blog.domain.Comment commentDomain = ModelUtil.modelMapper.map(comment1, com.example.blog.domain.Comment.class);
        given(commentService.createComment(commentDomain)).willReturn(commentDomain);

        mockMvc.perform(post("/api/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(comment1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(comment1.getTitle()))
                .andDo(print());
    }


    @Test
    public void testDeleteComment() throws Exception {
        doNothing().when(commentService).deleteComment(comment1.getId());

        mockMvc.perform(delete("/api/comment/{id}", comment1.getId()))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}

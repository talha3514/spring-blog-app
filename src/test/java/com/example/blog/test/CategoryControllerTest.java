package com.example.blog.test;

import com.example.blog.controller.CategoryController;
import com.example.blog.entity.Category;
import com.example.blog.service.CategoryService;
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

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    private Category category1;
    private Category category2;

    @BeforeEach
    public void setUp() {
        category1 = new Category();
        category1.setId(1L);
        category1.setTitle("cat1");
        category1.setContent("cat1");

        category2 = new Category();
        category2.setId(2L);
        category1.setTitle("cat2");
        category1.setContent("cat2");
    }

    @Test
    public void testGetAllCategories() throws Exception {
        List<Category> allCategoryEntity = Arrays.asList(category1, category2);

        List<com.example.blog.domain.Category> allCategory = allCategoryEntity.stream()
                .map(x -> ModelUtil.modelMapper.map(x, com.example.blog.domain.Category.class))
                .toList();

        given(categoryService.getAllCategories()).willReturn(allCategory);

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(category1.getTitle()))
                .andExpect(jsonPath("$[1].title").value(category2.getTitle()))
                .andDo(print());
    }

    @Test
    public void testGetCategoryById() throws Exception {
        Optional<com.example.blog.domain.Category> categoryDomain =
                Optional.ofNullable(ModelUtil.modelMapper.map(category1, com.example.blog.domain.Category.class));
        given(categoryService.getCategoryById(category1.getId())).willReturn(categoryDomain);

        Long id = category1.getId();

        mockMvc.perform(get("/api/categories/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(category1.getTitle()))
                .andDo(print());
    }

    @Test
    public void testCreateCategory() throws Exception {
        com.example.blog.domain.Category categoryDomain = ModelUtil.modelMapper.map(category1, com.example.blog.domain.Category.class);
        given(categoryService.createCategory(categoryDomain)).willReturn(categoryDomain);

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categoryDomain)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(category1.getTitle()))
                .andDo(print());
    }

    @Test
    public void testDeleteCategory() throws Exception {
        doNothing().when(categoryService).deleteCategory(category1.getId());

        mockMvc.perform(delete("/api/categories/{id}", category1.getId()))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}

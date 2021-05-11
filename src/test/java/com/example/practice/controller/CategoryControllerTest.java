package com.example.practice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.practice.model.CategoryRequestModel;
import com.example.practice.model.CategoryResponseModel;
import com.example.practice.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CategoryService service;
	
	@Test
	void testCreateCategory() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		CategoryResponseModel response = new CategoryResponseModel("Book", "This is a Book Category", "user", new Date(), null, null);
		Mockito.when(service.createCategory(Mockito.any(CategoryRequestModel.class))).thenReturn(response);
		CategoryRequestModel request = new CategoryRequestModel("Book", "This belongs to Books Category");
		
		String url = "/api/category";
		mockMvc.perform(post(url)
				.contentType("application/json")
				.content(mapper.writeValueAsString(request)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.categoryName").value("Book"))
				.andExpect(jsonPath("$.description").value("This is a Book Category"))
				.andExpect(jsonPath("$.createdBy").value("user"));
	}
	
	@Test
	void testUpdateCategory() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		CategoryRequestModel request = new CategoryRequestModel("Book Updated", "This belongs to Books Category");
		CategoryResponseModel response = new CategoryResponseModel("Book Updated", "This is a Book Category", "user", new Date(), "user", new Date());
		Mockito.when(service.updateCategory(Mockito.any(CategoryRequestModel.class))).thenReturn(response);
		
		mockMvc.perform(put("/api/category/{id}", 1)
				.contentType("application/json")
				.content(mapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.categoryName").value("Book Updated"));
	}
	
	@Test
	void testGetCategoryList() throws Exception {
		CategoryResponseModel response1 = new CategoryResponseModel("Book", "This is a Book Category", "user", new Date(), null, null);
		CategoryResponseModel response2= new CategoryResponseModel("Mobile", "This is a Mobile Category", "user", new Date(), null, null);
		CategoryResponseModel response3 = new CategoryResponseModel("Games", "This is a Games Category", "user", new Date(), null, null);
		
		List<CategoryResponseModel> list = new ArrayList<CategoryResponseModel>();
		
		list.add(response1);
		list.add(response2);
		list.add(response3);
		
		Mockito.when(service.getCategoryList()).thenReturn(list);		
		mockMvc.perform(get("/api/category"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].categoryName").value("Book"))
		.andExpect(jsonPath("$[1].categoryName").value("Mobile"))
		.andExpect(jsonPath("$[2].categoryName").value("Games"));
	 }
	
	@Test
	public void deleteCategory() throws Exception
	{
		mockMvc.perform(delete("/api/category/{id}", 1))
		.andExpect(status().isNoContent());
	}
	
	
	@Test
	public void getByCategoryName() throws Exception{
		CategoryResponseModel response = new CategoryResponseModel("Book", "This is a Book Category", "user", new Date(), null, null);
		Mockito.when(service.getCategoryByName("Book")).thenReturn(response);
		mockMvc.perform(get("/api/category/Book"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.categoryName").value("Book"));
	}
}
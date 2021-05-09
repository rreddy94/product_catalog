package com.example.practice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.practice.entity.Category;
import com.example.practice.model.CategoryRequestModel;
import com.example.practice.model.CategoryResponseModel;
import com.example.practice.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class CategoryServiceTest {

	@MockBean
	private CategoryRepository repository;
	
	@InjectMocks
	private CategoryServiceImpl service;
	
	
	@Test
	void testCreateCategory() {
		CategoryRequestModel request = new CategoryRequestModel("Book", "This belongs to Books Category");
		Category category = new Category("Book", "This belongs to Books Category", "user", new Date());
		Category responseCategory = new Category("Book", "This belongs to Books Category", "user", new Date());
		Mockito.when(repository.save(category)).thenReturn(responseCategory);
		CategoryResponseModel response = service.createCategory(request);
		assertThat(response.getCategoryName()).isEqualTo("Book");
		}



}

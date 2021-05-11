package com.example.practice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.practice.entity.Category;
import com.example.practice.model.CategoryRequestModel;
import com.example.practice.model.CategoryResponseModel;
import com.example.practice.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class CategoryServiceTest 
{

	@MockBean
	private CategoryRepository repository;
	
	@InjectMocks
	private CategoryServiceImpl service;
	
	private ModelMapper mapper;
	
	@BeforeEach
	public void createMapper()
	{
		mapper = new ModelMapper();
		service.setMapper(mapper);
	}
	
	@Test
	void testCreateCategory()
	{
		CategoryRequestModel request = new CategoryRequestModel("Book", "This belongs to Books Category");
		Category category = new Category("Book", "This belongs to Books Category", "user", new Date());
		
		Mockito.when(repository.save(Mockito.any(Category.class))).thenReturn(category);
		Mockito.when(repository.existsByCategoryName(Mockito.anyString())).thenReturn(false);
		CategoryResponseModel response = service.createCategory(request);
		assertThat(response.getCategoryName()).isEqualTo("Book");
	}
	
	@Test
	void testCreateAlreadyExistingCategory()
	{
		CategoryRequestModel request = new CategoryRequestModel("Book", "This belongs to Books Category");
		Mockito.when(repository.existsByCategoryName(Mockito.anyString())).thenReturn(true);
		CategoryResponseModel response = service.createCategory(request);
		assertThat(response).isNull();
	}
		
	@Test
	void testUpdateCategory(){
		CategoryRequestModel updateRequest = new CategoryRequestModel("Book", "This belongs to Books Category");
		updateRequest.setId(1);
		Category category = new Category("Book", "This belongs to Books Category", "user", new Date());
		
		Mockito.when(repository.existsById(Mockito.anyInt())).thenReturn(true);
		Mockito.when(repository.save(Mockito.any(Category.class))).thenReturn(category);
		CategoryResponseModel response = service.updateCategory(updateRequest);
		assertThat(response.getCategoryName()).isEqualTo("Book");
	}
	
	@Test
	void testUpdateNullCategory(){
		CategoryRequestModel updateRequest = new CategoryRequestModel("Book Updated", "This belongs to Books Category");
	   Mockito.when(repository.existsById(Mockito.anyInt())).thenReturn(false);
		CategoryResponseModel response = service.updateCategory(updateRequest);
		assertThat(response).isNull();
	}
	
	@Test
	void testDeleteCategory() {
		service.deleteCategory(1);
		verify(repository,times(1)).deleteById(1);
	}
	
	
	@Test
	void testGetCategoryList(){
		Category category1 = new Category("Book", "This belongs to Books Category", "user", new Date());
		Category category2 = new Category("Mobile", "This belongs to Mobile Category", "user", new Date());
		Category category3 = new Category("Games", "This belongs to Games Category", "user", new Date());		
		
		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(category1);
		categoryList.add(category2);
		categoryList.add(category3);
		Mockito.when(repository.findAll()).thenReturn(categoryList);
		List<CategoryResponseModel> responseList = service.getCategoryList();
		
		assertThat(responseList).size().isGreaterThan(0);
		assertThat(responseList.get(0)).isNotNull();
		assertThat(responseList.get(0).getCategoryName()).isEqualTo("Book");
	}
	
	@Test
	void testGetCategoryByName()
	{
		Category category = new Category("Book", "This belongs to Books Category", "user", new Date());
		Mockito.when(repository.findByCategoryName("Book")).thenReturn(category);
		CategoryResponseModel response = service.getCategoryByName("Book");
		assertThat(response.getCategoryName()).isNotNull();
	}
	
	@Test
	void testNullCategory()
	{
		Mockito.when(repository.findByCategoryName("Book")).thenReturn(null);
		CategoryResponseModel response = service.getCategoryByName("Book");
		assertThat(response).isNull();
	}


}
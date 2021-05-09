package com.example.practice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.practice.entity.Category;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void testSaveCategory() 
	{
	  Category category = new Category("Book", "This category has books", "user", new Date());
	  Category responseCategory = repository.save(category);
	
	  assertThat(responseCategory.getId()).isNotNull();
	}
	
	@Test
	public void testUpdateCategory()
	{
		Category request = repository.findByCategoryName("Book");
		request.setCreatedBy("user has changed");
		repository.save(request);
		Category updatedRequest = repository.findByCategoryName("Book");
		assertThat(updatedRequest.getCreatedBy()).isEqualTo("user has changed");
	}
	
	
	@Test
	public void testDeleteCategory()
	{
		repository.deleteById(1);
		Optional<Category> category = repository.findById(1);
		assertThat(category.isEmpty()).isTrue();		
	}
	
	@Test
	public void testFindCategoryByName()
	{
		Category category = repository.findByCategoryName("Book");
		
		assertThat(category.getCategoryName()).isEqualTo("Book");
	}
	
	@Test
	public void testListCategories()
	{
		List<Category> categoryList = repository.findAll();
		assertThat(categoryList).size().isGreaterThan(0);
	
	}
}
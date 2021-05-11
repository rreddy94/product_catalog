package com.example.practice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
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
	@Order(1)
	public void testSaveCategory()
	{
	  Category category = new Category("Book Test", "This category has books", "user", new Date());
	  Category responseCategory = repository.save(category);
	
	  assertThat(responseCategory.getId()).isNotNull();
	}
	
	@Test
	@Order(2)
	public void testUpdateCategory()
	{
		Category category = new Category("Mobile Test", "This category has books", "user", new Date());
		repository.save(category);
		
		Category request = repository.findByCategoryName("Mobile Test");
		request.setCreatedBy("user has changed");
		Category updatedRequest = repository.save(request);
		
		assertThat(updatedRequest.getCreatedBy()).isEqualTo("user has changed");
	}
	
	@Test
	@Order(3)
	public void testDeleteCategory()
	{
		Category category = new Category("Mobiles", "This category has Mobiles", "user", new Date());
		Category responseCategory = repository.save(category);
		repository.deleteById(responseCategory.getId());
		
		Optional<Category> deletedCategory = repository.findById(responseCategory.getId());
		assertThat(deletedCategory.isEmpty()).isTrue();		
	}
	
	@Test
	@Order(4)
	public void testFindCategoryByName()
	{
		Category category = new Category("Book Test", "This category has Mobiles", "user", new Date());
		repository.save(category);
		
		Category responseCategory = repository.findByCategoryName("BookTest");
		
		assertThat(responseCategory.getCategoryName()).isEqualTo("BookTest");
	}

	@Test
	@Order(5)
	public void testListCategories()
	{
		Category category = new Category("Book Test", "This category has Mobiles", "user", new Date());
		repository.save(category);
		  List<Category> categoryList = repository.findAll();
		assertThat(categoryList).size().isGreaterThan(0);
	}

    @Test
    @Order(6)
    public void testExistByCategoryName()
    {
    	Category category = new Category("Book Test", "This category has books", "user", new Date());
    	repository.save(category);
    	
    	boolean isExists = repository.existsByCategoryName("Book Test");
    	assertThat(isExists).isTrue();
    }
}
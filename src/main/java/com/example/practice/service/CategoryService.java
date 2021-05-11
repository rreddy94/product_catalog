package com.example.practice.service;

import java.util.List;

import com.example.practice.entity.Category;
import com.example.practice.model.CategoryRequestModel;
import com.example.practice.model.CategoryResponseModel;


public interface CategoryService {
	
	public CategoryResponseModel createCategory(CategoryRequestModel request);
	
	public CategoryResponseModel updateCategory(CategoryRequestModel request);
	
	public void deleteCategory(Integer id);
	
	public List<CategoryResponseModel> getCategoryList();
	
	public CategoryResponseModel getCategoryByName(String categoryName);

}
package com.example.practice.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryRequestModel {
	
	private Integer id;

	@NotNull(message="Category Name Cannot be empty")
	@Size(min=3, max=30, message="Size must be greater than 3 and less than 30")
	private String categoryName;
	
	@Size(min=3, max=30, message="Size must be greater than 3 and less than 30")
	private String description;

	public CategoryRequestModel() {
	}
	
	public CategoryRequestModel(String categoryName, String description) {
		this.categoryName = categoryName;
		this.description = description;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return "CategoryRequestModel [categoryName=" + categoryName + ", description=" + description + "]";
	}
	
}
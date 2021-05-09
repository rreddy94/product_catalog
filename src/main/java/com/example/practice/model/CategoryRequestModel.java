package com.example.practice.model;

public class CategoryRequestModel {
	
	private Integer id;

	private String categoryName;
	
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
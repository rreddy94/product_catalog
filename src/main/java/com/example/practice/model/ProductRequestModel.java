package com.example.practice.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductRequestModel {

	private int id;
	
	@NotNull(message="Product Name Cannot be empty")
	@Size(min=3, max=30, message="Size must be greater than 3 and less than 30")
	private String productName;
	
	@Size(min=3, max=30, message="Size must be greater than 3 and less than 30")
	private String description;
	
	@NotNull(message="Price Cannot be empty")
	private Float price;
	
	@NotNull(message="Quantity Cannot be empty")
	private Integer quantity;
	
	@NotNull(message="Category Name Cannot be empty")
	@Size(min=3, max=30, message="Size must be greater than 3 and less than 30")
	private String categoryName;
	
	private Integer categoryId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
}

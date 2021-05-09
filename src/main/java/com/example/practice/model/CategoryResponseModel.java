package com.example.practice.model;

import java.util.Date;

public class CategoryResponseModel {

	private String categoryName;

	private String description;

	private String createdBy;

	private Date createdOn;

	private String updatedBy;

	private Date updatedOn;

	public CategoryResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryResponseModel(String categoryName, String description, String createdBy, Date createdOn,
			String updatedBy, Date updatedOn) {
		super();
		this.categoryName = categoryName;
		this.description = description;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
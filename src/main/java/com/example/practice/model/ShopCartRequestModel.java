package com.example.practice.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ShopCartRequestModel {
	
	
	@NotNull(message="User Id Cannot be empty")
	@Min(1)
	private Integer userId;
	
	@NotNull(message="Product Name Cannot be empty")
	@Size(min=3, max=30, message="Product Name must be greater than 3 and less than 30")
	private String productName;
	
	private Integer cartId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	
}
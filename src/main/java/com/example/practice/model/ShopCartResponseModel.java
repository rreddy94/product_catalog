package com.example.practice.model;

import java.util.Set;

import com.example.practice.entity.Product;

public class ShopCartResponseModel {
	
	private Integer cartId;
	
	private Integer userId;
	
	private Set<Product> productList;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Set<Product> getProductList() {
		return productList;
	}

	public void setProductList(Set<Product> productList) {
		this.productList = productList;
	}
	
}
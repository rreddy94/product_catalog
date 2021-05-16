package com.example.practice.service;

import java.util.List;

import com.example.practice.model.ShopCartRequestModel;
import com.example.practice.model.ShopCartResponseModel;


public interface ShopCartService {
	
	public ShopCartResponseModel addShopCart(ShopCartRequestModel request);
	
	public void deleteShopCart(Integer id);
	
	public List<ShopCartResponseModel> getShopCartList();
	
	public ShopCartResponseModel getShopCartByUserId(Integer id);
	
	public ShopCartResponseModel getShopCartById(Integer id);
	
}
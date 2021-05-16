package com.example.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practice.entity.ShopCart;

public interface ShopCartRepository extends JpaRepository<ShopCart, Integer>  {

	public ShopCart findByUserId(Integer id);
	

}
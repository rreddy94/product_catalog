package com.example.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Product findByProductName(String name);
	
	public boolean existsByProductName(String name);


}
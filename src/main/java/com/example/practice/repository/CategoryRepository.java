package com.example.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practice.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

     public Category findByCategoryName(String categoryName);

}
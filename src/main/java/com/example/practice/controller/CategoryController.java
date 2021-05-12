package com.example.practice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.model.CategoryRequestModel;
import com.example.practice.model.CategoryResponseModel;
import com.example.practice.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	
	@PostMapping(value = "/category",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequestModel request)
	{
		CategoryResponseModel response = service.createCategory(request);
		return new ResponseEntity<CategoryResponseModel>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/category/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable(value = "id") Integer id,@Valid @RequestBody CategoryRequestModel request)
	{
		request.setId(id);
		CategoryResponseModel response = service.updateCategory(request);
		return new ResponseEntity<CategoryResponseModel>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/category/{id}")
	public ResponseEntity deleteCategory(@PathVariable(value = "id") Integer id) {
		service.deleteCategory(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/category")
	public ResponseEntity<?> getCategoryList()
	{
	   List<CategoryResponseModel> categoryList = service.getCategoryList();
	   return new ResponseEntity<List<CategoryResponseModel>>(categoryList, HttpStatus.OK);
	}
	
	
	@GetMapping("/category/{name}")
	public ResponseEntity<CategoryResponseModel> getCategoryByName(@PathVariable(value = "name") String name)
	{
		CategoryResponseModel response = service.getCategoryByName(name);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	
	
}
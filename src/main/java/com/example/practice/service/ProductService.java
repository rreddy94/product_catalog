package com.example.practice.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.example.practice.model.ProductRequestModel;
import com.example.practice.model.ProductResponseModel;


public interface ProductService {

   public ProductResponseModel createProduct(ProductRequestModel request);
	
	public ProductResponseModel updateProduct(ProductRequestModel request);
	
	public void deleteProduct(Integer id);
	
	public List<ProductResponseModel> getProductList();
	
	public ProductResponseModel getProductByName(String categoryName);
	
	public ByteArrayInputStream exportToExcel();
	
	public ByteArrayInputStream exportToPdf();
	
}
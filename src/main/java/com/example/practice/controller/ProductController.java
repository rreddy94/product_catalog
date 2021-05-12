package com.example.practice.controller;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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

import com.example.practice.model.ProductRequestModel;
import com.example.practice.model.ProductResponseModel;
import com.example.practice.service.ProductService;


@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping(value = "/product",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequestModel request){
		ProductResponseModel response = service.createProduct(request);
		return new ResponseEntity<ProductResponseModel>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable(value = "id") Integer id, @Valid @RequestBody ProductRequestModel request)
	{
		request.setId(id);
		ProductResponseModel response = service.updateProduct(request);
		return new ResponseEntity<ProductResponseModel>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity deleteProduct(@PathVariable(value = "id") Integer id) {
		service.deleteProduct(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/product")
	public ResponseEntity<?> getCategoryList()
	{
	   List<ProductResponseModel> productList = service.getProductList();
	   return new ResponseEntity<List<ProductResponseModel>>(productList, HttpStatus.OK);
	}
	
	@GetMapping("/product/{name}")
	public ResponseEntity<ProductResponseModel> getProductByName(@PathVariable(value = "name") String name)
	{
		ProductResponseModel response = service.getProductByName(name);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/products/exporttoexcel")
	public ResponseEntity<?> exportProductsToExcel(HttpServletResponse response){
		ByteArrayInputStream input = service.exportToExcel();
		
		HttpHeaders headers = new HttpHeaders();
		String fileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		headers.add("Content-Disposition", "attachment; filename=products_" + fileName + ".xlsx");
		headers.add("Content-Type", "application/vnd.ms-excel");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(input));
	}
	
	@GetMapping("/products/exporttopdf")
	public ResponseEntity<?> exportProductsToPdf(HttpServletResponse response){
		
		ByteArrayInputStream input = service.exportToPdf();
		HttpHeaders headers = new HttpHeaders();
		String fileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		headers.add("Content-Disposition", "attachment; filename=products_" + fileName + ".pdf");
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(input));
	}
}
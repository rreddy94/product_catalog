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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.model.ShopCartRequestModel;
import com.example.practice.model.ShopCartResponseModel;
import com.example.practice.service.ShopCartService;

@RestController
@RequestMapping("/api")
public class ShopCartController {
	
	@Autowired
	private ShopCartService service;
	
	@PostMapping(value = "/cart",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createShopCart(@Valid @RequestBody ShopCartRequestModel request)
	{
		ShopCartResponseModel response = service.addShopCart(request);
		return new ResponseEntity<ShopCartResponseModel>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cart/{id}")
	public ResponseEntity deleteShopCart(@PathVariable(value = "id") Integer id) {
		service.deleteShopCart(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<?> getShopCartList()
	{
	   List<ShopCartResponseModel> roleList = service.getShopCartList();
	   return new ResponseEntity<List<ShopCartResponseModel>>(roleList, HttpStatus.OK);
	}
	
	@GetMapping("/cart/user/{id}")
	public ResponseEntity<ShopCartResponseModel> getShopCartByUser(@PathVariable(value = "id") Integer id)
	{
		ShopCartResponseModel response = service.getShopCartByUserId(id);
		return new ResponseEntity<ShopCartResponseModel>(response, HttpStatus.OK);	
	}
	
	@GetMapping("/cart/{id}")
	public ResponseEntity<ShopCartResponseModel> getShopCartById(@PathVariable(value = "id") Integer id)
	{
		ShopCartResponseModel response = service.getShopCartById(id);
		return new ResponseEntity<ShopCartResponseModel>(response, HttpStatus.OK);	
	}

}
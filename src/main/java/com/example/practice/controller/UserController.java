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

import com.example.practice.model.UserRequestModel;
import com.example.practice.model.UserResponseModel;
import com.example.practice.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	
	@Autowired
	private UserService service;
	
	@PostMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestModel request)
	{
		UserResponseModel response = service.createUser(request);
		return new ResponseEntity<UserResponseModel>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable(value = "id") Integer id,@Valid @RequestBody UserRequestModel request)
	{
		request.setId(id);
		UserResponseModel response = service.updateUser(request);
		return new ResponseEntity<UserResponseModel>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity deleteUser(@PathVariable(value = "id") Integer id) {
		service.deleteUser(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getUserList()
	{
	   List<UserResponseModel> userList = service.getUserList();
	   return new ResponseEntity<List<UserResponseModel>>(userList, HttpStatus.OK);
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<UserResponseModel> getUserByName(@PathVariable(value = "name") String name)
	{
		UserResponseModel response = service.getUserByName(name);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
}
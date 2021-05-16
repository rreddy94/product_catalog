package com.example.practice.service;

import java.util.List;

import com.example.practice.model.UserRequestModel;
import com.example.practice.model.UserResponseModel;


public interface UserService {

	public UserResponseModel createUser(UserRequestModel request);
	
	public UserResponseModel updateUser(UserRequestModel request);
	
	public void deleteUser(Integer id);
	
	public List<UserResponseModel> getUserList();
	
	public UserResponseModel getUserByName(String userName);
	
	public UserResponseModel getUserByEmail(String email);
	
	public UserResponseModel getUserById(Integer id);
}
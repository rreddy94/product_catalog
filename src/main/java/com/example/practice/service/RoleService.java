package com.example.practice.service;

import java.util.List;

import com.example.practice.model.RoleRequestModel;
import com.example.practice.model.RoleResponseModel;


public interface RoleService {
	
	public RoleResponseModel createRole(RoleRequestModel request);
	
	public RoleResponseModel updateRole(RoleRequestModel request);
	
	public void deleteRole(Integer id);
	
	public List<RoleResponseModel> getRoleList();
	
	public RoleResponseModel getRoleByName(String roleName);

}
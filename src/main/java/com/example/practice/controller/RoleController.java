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

import com.example.practice.model.RoleRequestModel;
import com.example.practice.model.RoleResponseModel;
import com.example.practice.service.RoleService;

@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
	private RoleService service;
	
	@PostMapping(value = "/role",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createRole(@Valid @RequestBody RoleRequestModel request)
	{
		RoleResponseModel response = service.createRole(request);
		return new ResponseEntity<RoleResponseModel>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable(value = "id") Integer id,@Valid @RequestBody RoleRequestModel request)
	{
		request.setId(id);
		RoleResponseModel response = service.updateRole(request);
		return new ResponseEntity<RoleResponseModel>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/role/{id}")
	public ResponseEntity deleteRole(@PathVariable(value = "id") Integer id) {
		service.deleteRole(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/role")
	public ResponseEntity<?> getRoleList()
	{
	   List<RoleResponseModel> roleList = service.getRoleList();
	   return new ResponseEntity<List<RoleResponseModel>>(roleList, HttpStatus.OK);
	}
	
	@GetMapping("/role/{name}")
	public ResponseEntity<RoleResponseModel> getRoleByName(@PathVariable(value = "name") String name)
	{
		RoleResponseModel response = service.getRoleByName(name);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
}
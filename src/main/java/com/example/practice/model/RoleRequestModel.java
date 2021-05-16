package com.example.practice.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoleRequestModel {
	private Integer id;
	
	@NotNull(message="Role Name Cannot be empty")
	@Size(min=3, max=30, message="Size must be greater than 3 and less than 30")
	private String roleName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
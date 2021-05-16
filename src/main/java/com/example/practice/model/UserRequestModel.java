package com.example.practice.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequestModel {
	
	private Integer id;
	
	@NotNull(message="Last Name Cannot be empty")
	@Size(min=3, max=30, message="First Name length must be greater than 3 and less than 30")
	private String firstName;
	
	@NotNull(message="Last Name Cannot be empty")
	@Size(min=3, max=30, message="Last Name length must be greater than 3 and less than 30")
	private String lastName;
	
	@Email
	private String email;
	
	@NotNull(message="Password Cannot be empty")
	@Size(min=10, max=15, message="Password length must be greater than 10 and less than 15")
	private String password;
	
	@NotNull(message="Role Name Cannot be empty")
	private String roleName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
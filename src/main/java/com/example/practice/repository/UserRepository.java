package com.example.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practice.entity.User;

public interface UserRepository  extends JpaRepository<User, Integer>{
	
	public User findByFirstName(String firstName);
    
    public boolean existsByFirstName(String roleName);
    
    public boolean existsByEmail(String email);
    
    public User findByEmail(String email);

}
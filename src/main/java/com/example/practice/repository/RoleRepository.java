package com.example.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practice.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

     public Role findByRoleName(String roleName);
     
     public boolean existsByRoleName(String roleName);

}
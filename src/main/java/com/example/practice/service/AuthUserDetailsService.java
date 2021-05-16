package com.example.practice.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.practice.entity.User;
import com.example.practice.repository.UserRepository;

@Service
public class AuthUserDetailsService  implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	Logger logger = LoggerFactory.getLogger(ShopCartServiceImpl.class);
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User userEntity = repository.findByEmail(email);
		logger.debug("Email from the request is " + email + " User Entity Obtained is " + userEntity);
		
		if(userEntity == null)
			throw new UsernameNotFoundException(email);
		
		String roleName = userEntity.getRole().getRoleName();
		
		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), 
				userEntity.getPassword(), getAuthorities(roleName));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(String role)
	{
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}
}
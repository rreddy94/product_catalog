package com.example.practice.utility;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.practice.service.AuthUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private AuthUserDetailsService userDetailsService;

	public SecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder,
			AuthUserDetailsService userDetailsService) {
	
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userDetailsService = userDetailsService;
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
						 .antMatchers("/api/user/**","/api/role/**").permitAll()
						 .anyRequest().authenticated()
						 .and()
						 .addFilter(new AuthenticationFilter(authenticationManager()))
						 .addFilter(new AuthorizationFilter(authenticationManager()))
						 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	
	
}
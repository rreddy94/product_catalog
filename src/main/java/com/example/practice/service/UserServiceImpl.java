package com.example.practice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.practice.entity.Role;
import com.example.practice.entity.User;
import com.example.practice.entity.UserHistory;
import com.example.practice.exception.EntityExistsException;
import com.example.practice.exception.EntityNotFoundException;
import com.example.practice.model.UserRequestModel;
import com.example.practice.model.UserResponseModel;
import com.example.practice.repository.RoleRepository;
import com.example.practice.repository.UserHistoryRepository;
import com.example.practice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private RoleRepository roleRepository;
		
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserHistoryRepository userHistRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public ModelMapper getMapper() {
		return mapper;
	}

	public void setMapper(ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public UserResponseModel createUser(UserRequestModel request) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		boolean isUserExists = userRepository.existsByEmail(request.getFirstName());
		if (isUserExists) {
			throw new EntityExistsException("User Already Exists");
		} else {
			Role role = roleRepository.findByRoleName(request.getRoleName());
			if (role == null) {
				throw new EntityNotFoundException("Role doesnot Exists thus cannot create the Create");
			} else {
				User entityRequest = mapper.map(request, User.class);
				entityRequest.setCreatedBy("user");
				entityRequest.setCreatedOn(new Date());
				entityRequest.setPassword(encoder.encode(request.getPassword()));
				entityRequest.setRole(role);

				User entityResponse = userRepository.save(entityRequest);
				UserResponseModel response = mapper.map(entityResponse, UserResponseModel.class);
				return response;
			}
		}
	}

	public UserResponseModel updateUser(UserRequestModel request)
	{
		Optional<User> optionalUser = userRepository.findById(request.getId());
		logger.debug("Request Id and the user status is " + request.getId() + "status" + optionalUser);
		if(optionalUser.isEmpty())
		{
		  throw new EntityNotFoundException("User is not found");
		}
		else {
			
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			User entity = optionalUser.get();
			entity.setFirstName(request.getFirstName());
			entity.setLastName(request.getLastName());
			entity.setPassword(request.getPassword());
			entity.setUpdatedBy("user");
			entity.setUpdatedOn(new Date());
			User responseEntity = userRepository.save(entity);
			
			UserHistory userHistoryEntity = new UserHistory();
			userHistoryEntity.setDescription("User Details are updated");
			userHistoryEntity.setCreatedBy("user");
			userHistoryEntity.setCreatedOn(new Date());
			userHistoryEntity.setUser(entity);
			
			userHistRepository.save(userHistoryEntity);
			
			return mapper.map(responseEntity, UserResponseModel.class);
		}
	}

	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<UserResponseModel> getUserList()
	{
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<UserResponseModel> responseList = userRepository.findAll()
		.stream()
		.map( e -> mapper.map(e, UserResponseModel.class)).collect(Collectors.toList());
		return responseList;
	}

	@Override
	public UserResponseModel getUserByName(String userName) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User entity = userRepository.findByFirstName(userName);
		if(entity != null)
		{
		   return mapper.map(entity, UserResponseModel.class);
		}
		else{
			throw new EntityNotFoundException("Requested User is not found");
		}
	 }
	
	public UserResponseModel getUserByEmail(String email) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User entity = userRepository.findByEmail(email);
		if(entity != null)
		{
		   return mapper.map(entity, UserResponseModel.class);
		}
		else{
			throw new EntityNotFoundException("Requested User is not found");
		}
	 }
	
	public UserResponseModel getUserById(Integer id) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Optional<User> entity = userRepository.findById(id);
		if(entity.isPresent())
		{
		   return mapper.map(entity.get(), UserResponseModel.class);
		}
		else{
			throw new EntityNotFoundException("Requested User is not found");
		}
	 }
	
}
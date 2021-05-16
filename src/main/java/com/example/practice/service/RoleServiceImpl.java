package com.example.practice.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.practice.entity.Role;
import com.example.practice.exception.EntityExistsException;
import com.example.practice.exception.EntityNotFoundException;
import com.example.practice.model.RoleRequestModel;
import com.example.practice.model.RoleResponseModel;
import com.example.practice.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	public ModelMapper getMapper() {
		return mapper;
	}

	public void setMapper(ModelMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public RoleResponseModel createRole(RoleRequestModel request) {
		
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		boolean isExists = repository.existsByRoleName(request.getRoleName());
		
		if(isExists) {
		  throw new EntityExistsException("Provided Role Already Exists");
		}
		else {
			Role entityRequest = mapper.map(request, Role.class);
			entityRequest.setCreatedBy("user");
			entityRequest.setCreatedOn(new Date());

			Role entityResponse = repository.save(entityRequest);
			RoleResponseModel response = mapper.map(entityResponse, RoleResponseModel.class);
			return response;
		}
	}

	public RoleResponseModel updateRole(RoleRequestModel request) {
		// TODO Auto-generated method stub
		boolean isExists = repository.existsById(request.getId());
		if(!isExists) {
		  throw new EntityNotFoundException("Provided Role is not found");
		}else {
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			Role entity = mapper.map(request, Role.class);
			entity.setUpdatedBy("user");
			entity.setUpdatedOn(new Date());
			Role responseEntity = repository.save(entity);
			return mapper.map(responseEntity, RoleResponseModel.class);
		}
	}

	@Override
	public void deleteRole(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public List<RoleResponseModel> getRoleList()
	{
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<RoleResponseModel> responseList = repository.findAll()
		.stream()
		.map( e -> mapper.map(e, RoleResponseModel.class)).collect(Collectors.toList());
		return responseList;
	}

	@Override
	public RoleResponseModel getRoleByName(String roleName) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Role entity = repository.findByRoleName(roleName);
		if(entity != null)
		{
		   return mapper.map(entity, RoleResponseModel.class);
		}
		else{
			throw new EntityNotFoundException("Requested Role is not found");
		}
	 }
}
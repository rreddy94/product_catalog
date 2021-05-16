package com.example.practice.service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.practice.entity.Category;
import com.example.practice.exception.EntityExistsException;
import com.example.practice.exception.EntityNotFoundException;
import com.example.practice.model.CategoryRequestModel;
import com.example.practice.model.CategoryResponseModel;
import com.example.practice.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	public ModelMapper getMapper() {
		return mapper;
	}

	public void setMapper(ModelMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public CategoryResponseModel createCategory(CategoryRequestModel request) {
		
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		boolean isExists = repository.existsByCategoryName(request.getCategoryName());
		
		if(isExists) {
		  throw new EntityExistsException("Provided Category Already Exists");
		}
		else {
			Category entityRequest = mapper.map(request, Category.class);
			entityRequest.setCreatedBy("user");
			entityRequest.setCreatedOn(new Date());

			Category entityResponse = repository.save(entityRequest);
			CategoryResponseModel response = mapper.map(entityResponse, CategoryResponseModel.class);
			return response;
		}
	}

	public CategoryResponseModel updateCategory(CategoryRequestModel request) {
		// TODO Auto-generated method stub
		boolean isExists = repository.existsById(request.getId());
		
		if(!isExists) {
		  throw new EntityNotFoundException("Provided Category is not found");
		}else {
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			Category entity = mapper.map(request, Category.class);
			entity.setUpdatedBy("user");
			entity.setUpdatedOn(new Date());
			Category responseEntity = repository.save(entity);
			return mapper.map(responseEntity, CategoryResponseModel.class);
		}
	}

	@Override
	public void deleteCategory(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public List<CategoryResponseModel> getCategoryList()
	{
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<CategoryResponseModel> responseList = repository.findAll()
		.stream()
		.map( e -> mapper.map(e, CategoryResponseModel.class)).collect(Collectors.toList());
		return responseList;
	}

	
	@Override
	public CategoryResponseModel getCategoryByName(String categoryName) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Category entity = repository.findByCategoryName(categoryName);
		if(entity != null)
		{
		   return mapper.map(entity, CategoryResponseModel.class);
		}
		else{
			throw new EntityNotFoundException("Requested Category is not found");
		}
	 }
}
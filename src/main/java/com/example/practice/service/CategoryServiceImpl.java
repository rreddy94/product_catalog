package com.example.practice.service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.practice.entity.Category;
import com.example.practice.model.CategoryRequestModel;
import com.example.practice.model.CategoryResponseModel;
import com.example.practice.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	ModelMapper mapper = new ModelMapper();

	@Override
	public CategoryResponseModel createCategory(CategoryRequestModel request) {
		// TODO Auto-generated method stub
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Category entityRequest = mapper.map(request, Category.class);
		entityRequest.setCreatedBy("user");
		entityRequest.setCreatedOn(new Date());

		Category entityResponse = repository.save(entityRequest);
		CategoryResponseModel response = mapper.map(entityResponse, CategoryResponseModel.class);
		return response;
	}

	@Override
	public void updateCategory(CategoryRequestModel request) {
		// TODO Auto-generated method stub
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Category entity = mapper.map(request, Category.class);
		entity.setUpdatedBy("user");
		entity.setUpdatedOn(new Date());
		repository.save(entity);
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
		// TODO Auto-generated method stub
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Category entity = repository.findByCategoryName(categoryName);
		return mapper.map(entity, CategoryResponseModel.class);
	}

}
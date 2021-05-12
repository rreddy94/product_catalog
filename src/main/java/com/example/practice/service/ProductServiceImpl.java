package com.example.practice.service;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.practice.entity.Category;
import com.example.practice.entity.Product;
import com.example.practice.exception.EntityExistsException;
import com.example.practice.exception.EntityNotFoundException;
import com.example.practice.model.ProductRequestModel;
import com.example.practice.model.ProductResponseModel;
import com.example.practice.repository.CategoryRepository;
import com.example.practice.repository.ProductRepository;
import com.example.practice.utility.ExportToExcelUtility;
import com.example.practice.utility.ExportToPdfUtility;

@Service
public class ProductServiceImpl implements ProductService {
	
	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;
		
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ExportToExcelUtility excelUtility;
	
	@Autowired
	private ExportToPdfUtility pdfUtility;
	
	@Autowired
	private ModelMapper mapper;
	
	public ModelMapper getMapper() {
		return mapper;
	}

	public void setMapper(ModelMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public ProductResponseModel createProduct(ProductRequestModel request) {

		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		boolean isProductExists = productRepository.existsByProductName(request.getProductName());
		if (isProductExists) {
			throw new EntityExistsException("Provided Product Already Exists");
		} else {
			Category category = categoryRepository.findByCategoryName(request.getCategoryName());
			if (category == null) {
				throw new EntityNotFoundException("Category Doesnot Exists thus cannot create the Product");
			} else {
				Product entityRequest = mapper.map(request, Product.class);
				entityRequest.setCreatedBy("user");
				entityRequest.setCreatedOn(new Date());
				entityRequest.setCategory(category);

				Product entityResponse = productRepository.save(entityRequest);
				ProductResponseModel response = mapper.map(entityResponse, ProductResponseModel.class);
				return response;
			}
		}
	}

	public ProductResponseModel updateProduct(ProductRequestModel request)
	{
		Optional<Product> optionalProduct = productRepository.findById(request.getId());
		
		logger.debug("Request Id and the user status is " + request.getId() + "status" + optionalProduct);
		if(optionalProduct.isEmpty()) {
		  throw new EntityNotFoundException("Product is not found");
		}else {
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			Product entity = optionalProduct.get();
			entity.setProductName(request.getProductName());
			entity.setDescription(request.getDescription());
			entity.setPrice(request.getPrice());
			entity.setQuantity(request.getQuantity());
			entity.setUpdatedBy("user");
			entity.setUpdatedOn(new Date());
			Product responseEntity = productRepository.save(entity);
			return mapper.map(responseEntity, ProductResponseModel.class);
		}
	}

	@Override
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<ProductResponseModel> getProductList()
	{
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<ProductResponseModel> responseList = productRepository.findAll()
		.stream()
		.map( e -> mapper.map(e, ProductResponseModel.class)).collect(Collectors.toList());
		return responseList;
	}

	
	@Override
	public ProductResponseModel getProductByName(String productName) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Product entity = productRepository.findByProductName(productName);
		if(entity != null)
		{
		   return mapper.map(entity, ProductResponseModel.class);
		}
		else{
			throw new EntityNotFoundException("Requested Product is not found");
		}
	 }
	
	public ByteArrayInputStream exportToExcel(){
		List<Product> productList = productRepository.findAll();
		
		return excelUtility.productToExcel(productList);
	}
	
	public ByteArrayInputStream exportToPdf(){
		List<Product> productList = productRepository.findAll();	
		return pdfUtility.exportToPdf(productList);
	}
}
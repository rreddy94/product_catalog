package com.example.practice.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.practice.entity.Product;
import com.example.practice.entity.ShopCart;
import com.example.practice.entity.User;
import com.example.practice.exception.EntityNotFoundException;
import com.example.practice.model.ShopCartRequestModel;
import com.example.practice.model.ShopCartResponseModel;
import com.example.practice.repository.ProductRepository;
import com.example.practice.repository.ShopCartRepository;
import com.example.practice.repository.UserRepository;

@Service
public class ShopCartServiceImpl implements ShopCartService {

	Logger logger = LoggerFactory.getLogger(ShopCartServiceImpl.class);

	@Autowired
	private ShopCartRepository shopCartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;

	public ModelMapper getMapper() {
		return mapper;
	}

	public void setMapper(ModelMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public ShopCartResponseModel addShopCart(ShopCartRequestModel request) {

		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ShopCart cartEntity = shopCartRepository.findByUserId(request.getUserId());
		Product productEntity = productRepository.findByProductName(request.getProductName());
		Set<Product> productSet = new HashSet<Product>();
		ShopCart shopCartResponse = null;
		
		if (productEntity == null)
			throw new EntityNotFoundException("Product is not found hence cannot be added to the shopping cart");
		else {
			if (cartEntity != null) {
				if (cartEntity.getProductList() != null)
					cartEntity.getProductList().add(productEntity);
				else {
					productSet.add(productEntity);
					cartEntity.setProductList(productSet);
				}
				cartEntity.setUpdatedBy("user");
				cartEntity.setUpdatedOn(new Date());
				shopCartResponse = shopCartRepository.save(cartEntity);
			} else {
				Optional<User> optionalUser = userRepository.findById(request.getUserId());
				if (optionalUser.isEmpty()) {
					throw new EntityNotFoundException("User id doesnot exists cannot create the cart");
				}
				else {
					ShopCart shopCart = new ShopCart();
					productSet.add(productEntity);
					shopCart.setUser(optionalUser.get());
					shopCart.setProductList(productSet);
					shopCart.setCreatedBy("user");
					shopCart.setCreatedOn(new Date());
					shopCartResponse = shopCartRepository.save(shopCart);
				}
			}
		}
		ShopCartResponseModel response = mapper.map(shopCartResponse, ShopCartResponseModel.class);
		response.setCartId(shopCartResponse.getId());
		response.setUserId(shopCartResponse.getUser().getId());
		
		return response;
	}

	@Override
	public void deleteShopCart(Integer id) 
	{
		shopCartRepository.deleteById(id);
	}

	@Override
	public List<ShopCartResponseModel> getShopCartList() {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<ShopCartResponseModel> responseList = shopCartRepository.findAll().stream()
				.map(e -> mapper.map(e, ShopCartResponseModel.class)).collect(Collectors.toList());
		return responseList;
	}

	@Override
	public ShopCartResponseModel getShopCartByUserId(Integer userId) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ShopCart entity = shopCartRepository.findByUserId(userId);
		if (entity != null) {
			ShopCartResponseModel response = mapper.map(entity, ShopCartResponseModel.class);
			response.setCartId(entity.getId());
			response.setUserId(entity.getUser().getId());
			
			return response;
		} else {
			throw new EntityNotFoundException("Requested Shopping Cart is not found");
		}
	}
	
	public ShopCartResponseModel getShopCartById(Integer shopCartId) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Optional<ShopCart> entity = shopCartRepository.findById(shopCartId);
		
		if (entity.isPresent()) {
			ShopCartResponseModel response = mapper.map(entity, ShopCartResponseModel.class);
			response.setCartId(entity.get().getId());
			response.setUserId(entity.get().getUser().getId());
			
			return response;
		} else {
			throw new EntityNotFoundException("Requested Shopping Cart is not found");
		}
	}
}
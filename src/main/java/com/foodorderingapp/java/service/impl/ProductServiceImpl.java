package com.foodorderingapp.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodorderingapp.java.dto.ProductRequestDTO;
import com.foodorderingapp.java.dto.ProductResponseDTO;
import com.foodorderingapp.java.entity.Product;
import com.foodorderingapp.java.entity.ProductCategory;
import com.foodorderingapp.java.entity.Store;
import com.foodorderingapp.java.repo.ProductRepo;
import com.foodorderingapp.java.repo.StoreRepo;
import com.foodorderingapp.java.service.ProductService;
import com.foodorderingapp.java.service.dto.ProductDetails;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	StoreRepo storeRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Override
	public void saveProductDetails(ProductRequestDTO productRequestDTO) {
		Product product = new Product();
		BeanUtils.copyProperties(productRequestDTO, product);
		
		product.setProductCategory(ProductCategory.valueOf(productRequestDTO.getProductCategory()));
		
		Optional<Store> storeOptional =  storeRepo.findById(productRequestDTO.getStoreId());
		
		if(storeOptional.isPresent())
			product.setStore(storeOptional.get());
		
		productRepo.save(product);
	}

	@Override
	public ProductResponseDTO getAllProductsDetails(Integer storeId) {
			List<Product> productList = productRepo.findAll();
			
			List<ProductDetails> productDetailsList = productList.stream().map(product -> {
				
				//Modificar detalle (Java 8 Filter)
				if(product.getStore().getStoreId().equals(storeId)){
					ProductDetails productDetails = new ProductDetails();
					BeanUtils.copyProperties(product, productDetails);
					return productDetails;
				}
				
				return null;
			}).collect(Collectors.toList());
		
		ProductResponseDTO productResponseDTO = new ProductResponseDTO("Product List Fecth", 200);
		productResponseDTO.setProductsList(productDetailsList);
		
		return productResponseDTO;
	}
}

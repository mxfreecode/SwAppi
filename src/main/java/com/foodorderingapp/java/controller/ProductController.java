package com.foodorderingapp.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodorderingapp.java.dto.ProductRequestDTO;
import com.foodorderingapp.java.dto.ProductResponseDTO;
import com.foodorderingapp.java.dto.ResponseDTO;
import com.foodorderingapp.java.dto.StoreResponseDTO;
import com.foodorderingapp.java.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<ResponseDTO> saveProductDetails(@RequestBody ProductRequestDTO productRequestDTO){
		productService.saveProductDetails(productRequestDTO);
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO("Product Saved Success", 200), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/stores/{storeId}/products")
	public ResponseEntity<ResponseDTO> getAllProductDetails(@PathVariable Integer storeId){
		ProductResponseDTO productResponseDTO = productService.getAllProductsDetails(storeId);
		
		return new ResponseEntity<ResponseDTO>(productResponseDTO, HttpStatus.OK);
	}
}

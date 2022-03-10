package com.foodorderingapp.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodorderingapp.java.dto.StoreResponseDTO;
import com.foodorderingapp.java.entity.Store;
import com.foodorderingapp.java.service.StoreService;

@RestController
public class StoreController {
	
	@Autowired
	StoreService storeService;
	
	@GetMapping("/stores")
	public ResponseEntity<StoreResponseDTO> getAllStoreDetails(){
		StoreResponseDTO storeResponseDTO = storeService.getAllStoreDetails();
		return new ResponseEntity<StoreResponseDTO>(storeResponseDTO, HttpStatus.OK);
	}
}

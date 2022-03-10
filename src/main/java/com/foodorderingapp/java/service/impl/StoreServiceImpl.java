package com.foodorderingapp.java.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodorderingapp.java.dto.ResponseDTO;
import com.foodorderingapp.java.dto.StoreResponseDTO;
import com.foodorderingapp.java.entity.Store;
import com.foodorderingapp.java.repo.StoreRepo;
import com.foodorderingapp.java.service.StoreService;
import com.foodorderingapp.java.service.dto.StoreDetails;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	StoreRepo storeRepo;
	
	@Override
	public StoreResponseDTO getAllStoreDetails() {
		List<Store> storeList = storeRepo.findAll();
		
		List<StoreDetails> storeDetailsList = storeList.stream().map(store -> {
			StoreDetails storeDetails = new StoreDetails();
			BeanUtils.copyProperties(store, storeDetails);
			return storeDetails;
		}).collect(Collectors.toList());
		
		StoreResponseDTO storeResponseDTO = new StoreResponseDTO("Store Details Fetch Success", 200);
		storeResponseDTO.setStoresList(storeDetailsList);
		
		return storeResponseDTO;
	}
}

package com.foodorderingapp.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodorderingapp.java.dto.OrderRequestDTO;
import com.foodorderingapp.java.dto.OrderResponseDTO;
import com.foodorderingapp.java.dto.ResponseDTO;
import com.foodorderingapp.java.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/users/{userId}/orderdetails")
	public ResponseEntity<ResponseDTO> getAllOrderDetails(@PathVariable Integer userId){
		OrderResponseDTO orderResponseDTO = orderService.getAllOrderDetails(userId);
		
		return new ResponseEntity<ResponseDTO>(orderResponseDTO, HttpStatus.OK);
	}
	
	@PostMapping("/orderdetails")
	public ResponseEntity<ResponseDTO> saveOrderDetail(@RequestBody OrderRequestDTO orderRequestDTO){
		orderService.saveOrderDetail(orderRequestDTO);
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO("Order Saved Success", 200), HttpStatus.ACCEPTED);
	}
}

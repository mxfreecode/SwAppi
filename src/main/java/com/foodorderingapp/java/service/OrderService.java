package com.foodorderingapp.java.service;

import com.foodorderingapp.java.dto.OrderRequestDTO;
import com.foodorderingapp.java.dto.OrderResponseDTO;

public interface OrderService {

	OrderResponseDTO getAllOrderDetails(Integer userId);

	void saveOrderDetail(OrderRequestDTO orderRequestDTO);

}

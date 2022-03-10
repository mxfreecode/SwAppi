package com.foodorderingapp.java.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodorderingapp.java.dto.OrderRequestDTO;
import com.foodorderingapp.java.dto.OrderResponseDTO;
import com.foodorderingapp.java.entity.OrderDetail;
import com.foodorderingapp.java.entity.OrderProduct;
import com.foodorderingapp.java.entity.OrderStatus;
import com.foodorderingapp.java.entity.Product;
import com.foodorderingapp.java.entity.ProductCategory;
import com.foodorderingapp.java.entity.Store;
import com.foodorderingapp.java.repo.OrderRepo;
import com.foodorderingapp.java.repo.ProductRepo;
import com.foodorderingapp.java.repo.StoreRepo;
import com.foodorderingapp.java.service.OrderService;
import com.foodorderingapp.java.service.dto.OrderUserDetails;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	StoreRepo storeRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Override
	public OrderResponseDTO getAllOrderDetails(Integer userId) {
		List<OrderDetail> orderList = orderRepo.findAll();
		
		List<OrderUserDetails> ordertDetailsList = orderList.stream().map(order -> {
			
			if(order.getUserId().equals(userId)) {
				OrderUserDetails orderUserDetails = new OrderUserDetails();
				BeanUtils.copyProperties(order, orderUserDetails);
				
				List<Store> optionalStore = storeRepo.findAll();
				
				optionalStore.forEach(x -> {
					if(x.getStoreId().equals(order.getStoreId()))
						orderUserDetails.setStoreName(x.getStoreName());
				});
				
				orderUserDetails.setOrderDate(order.getOrderdate());
				orderUserDetails.setProductDetails(order.getOrderProductList());
				return orderUserDetails;
			}
			return null;
			
			
		}).collect(Collectors.toList());

		//orderList.forEach(x -> System.out.println("Data:" + x.getOrderProductList())); 
				
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO("Order List Success", 200);
		orderResponseDTO.setOrderUserDetails(ordertDetailsList);
		
		return orderResponseDTO;
	}

	@Override
	public void saveOrderDetail(OrderRequestDTO orderRequestDTO) {
		OrderDetail orderDetail = new OrderDetail();
		BeanUtils.copyProperties(orderRequestDTO, orderDetail);
		List<OrderProduct> productList = orderRequestDTO.getProductList();
		List<Product> orderProduct = productRepo.findAll();
		
		productList.forEach(x -> {
			orderProduct.forEach(y -> {
				if(y.getProductId().equals(x.getProductId()))
					x.setProductPrice(y.getProductPrice());
			});
		});
		
		
		orderDetail.setOrderProductList(orderRequestDTO.getProductList());	
		orderDetail.setOrderdate(LocalDate.now());
		orderDetail.setStatus(OrderStatus.PLACED);
		orderRepo.save(orderDetail);
	}

}

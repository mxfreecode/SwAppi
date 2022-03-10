package com.foodorderingapp.java.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.foodorderingapp.java.entity.OrderProduct;

public class OrderRequestDTO {
	private Integer userId;
	private Integer storeId;
	private LocalDate orderdate;
	private List<OrderProduct> productList = new ArrayList<>();
	private double totalPrice;
	private String instruction;
	private String status;
	
	public LocalDate getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(LocalDate orderdate) {
		this.orderdate = orderdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public List<OrderProduct> getProductList() {
		return productList;
	}
	public void setProductList(List<OrderProduct> productList) {
		this.productList = productList;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
}

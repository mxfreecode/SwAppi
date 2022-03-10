package com.foodorderingapp.java.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodorderingapp.java.entity.OrderDetail;

@Repository
public interface OrderRepo extends JpaRepository<OrderDetail, Integer>{

}

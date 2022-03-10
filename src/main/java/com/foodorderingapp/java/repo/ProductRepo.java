package com.foodorderingapp.java.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foodorderingapp.java.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	
}

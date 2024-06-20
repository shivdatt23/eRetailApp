package com.eretailapp.product_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eretailapp.product_service.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

	
}

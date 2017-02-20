package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.domain.Product;
import com.repository.ProductRepository;
import com.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Resource
	ProductRepository productRepository;
	
	@Override
	public Iterable<Product> listAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Integer id) {
		return productRepository.findOne(id);
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Integer id) {
		productRepository.delete(id);
	}

}

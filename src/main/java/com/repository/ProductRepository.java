package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
}

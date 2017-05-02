package com.service;

import com.domain.Product;

public interface ProductService {
    Iterable<Product> listAllProducts();
    
    Product getProductById(Integer id);
 
    Product saveProduct(Product product);
    
    void deleteProduct(Integer id);
    
    Product transactionalCheck(Product product) throws Exception;
    
    Product usingTransactions(Product product) throws Exception;
}

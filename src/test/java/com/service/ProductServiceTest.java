package com.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DemoApplication;
import com.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ProductServiceTest {

	@Resource
	public ProductService productService;
	
	@Test
	public void testNoTransactionManagement(){
		Product product = new Product();
		product.setDescription("should be saved");
		try {
			productService.transactionalCheck(product);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		assertEquals(product.getId(), productService.getProductById(product.getId()).getId());
	}
	
	@Test
	public void testTransactionManagement(){
		Product product = new Product();
		product.setDescription("should not be saved");
		try {
			productService.usingTransactions(product);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		assertEquals(null, productService.getProductById(product.getId()).getId());
	}

}

package com.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.domain.Product;
import com.service.ProductService;

@Controller
public class IndexController {

	
	@Resource
	ProductService productService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	String index(){
		return "index";
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("products", productService.listAllProducts());
		return "products";
	}
	
	@RequestMapping(value = "/product/new", method = RequestMethod.GET)
	public String newProduct(Model model){
		model.addAttribute("product", new Product());
		return "product-form";
	}
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public String showProduct(@PathVariable Integer id, Model model){
		model.addAttribute("product", productService.getProductById(id));
		return "product-show";
	}
	
	@RequestMapping(value = "/product/edit/{id}", method = RequestMethod.GET)
	public String editProduct(@PathVariable Integer id, Model model){
		model.addAttribute("product", productService.getProductById(id));
		return "product-form";
	}

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveProduct(Product product){
        productService.saveProduct(product);
        return "redirect:/product/" + product.getId();
    }
    
    

	
}

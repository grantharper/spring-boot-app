package com.controllers;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.domain.Product;
import com.service.ProductService;
import com.validators.ProductValidator;

@Controller
public class ProductController {

	@Resource
	ProductService productService;
	
	//@Autowired
	//@Qualifier("productValidator")
	@Autowired
	private ProductValidator productValidator;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String index() {
		return "index";
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("products", productService.listAllProducts());
		return "products";
	}

	@RequestMapping(value = "/product/new", method = RequestMethod.GET)
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		return "product-form";
	}

	@RequestMapping(value = "/product/show/{id}", method = RequestMethod.GET)
	public String showProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "product-show";
	}

	@RequestMapping(value = "/product/edit/{id}", method = RequestMethod.GET)
	public String editProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "product-form";
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model) {
		
		productValidator.validate(product, bindingResult);
		
		if(bindingResult.hasErrors()){
			return "product-form";
		}
		
		productService.saveProduct(product);
		return "redirect:/product/show/" + product.getId();
	}

	@RequestMapping(value = "/product/delete/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable Integer id, Model model) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
}

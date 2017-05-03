package com.controllers;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.FormFieldService;
import com.validators.FormFieldValidator;

@Controller
public class GenericController {
	
	@Resource
	FormFieldService formFieldService;
	@Autowired
	private FormFieldValidator formFieldValidator;
	
	@RequestMapping(value = "/generic-form", method = RequestMethod.GET)
	String getForm(Model model) {
		
		model.addAttribute("formFields", formFieldService.listAllFormFields());
		return "generic-form";
	}
	
	@RequestMapping(value = "/generic-form", method = RequestMethod.POST)
	String postForm(Model model, HttpServletRequest request) {
		Set<String> requestParams = request.getParameterMap().keySet();
		for(String param: requestParams){
			System.out.println(param);
		}
		//formFieldValidator.validate(obj, e);
		
		return "generic-form";
	}
	
}

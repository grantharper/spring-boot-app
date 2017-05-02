package com.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.domain.Product;

@Component
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "description", "description.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "price", "price.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "productId", "productId.empty");
		Product product = (Product) obj;
		
		if(!RegexValidation.descriptionPattern.matcher(product.getDescription()).matches()){
			e.rejectValue("description", "description.invalid");
		}
		if(!RegexValidation.urlPattern.matcher(product.getImageUrl()).matches()){
			e.rejectValue("imageUrl", "imageUrl.invalid");
		}
	}

}

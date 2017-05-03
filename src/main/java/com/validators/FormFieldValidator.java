package com.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.domain.FormField;

import static com.validators.RegexValidation.hasInvalidCharacters;

@Component
public class FormFieldValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FormField.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		
		FormField field = (FormField) obj;
		
		if(field.isRequired()){
			ValidationUtils.rejectIfEmptyOrWhitespace(e, field.getValue(), field.getName() + ".required");
			return;
		}
		
		if(field.getValidation() != null){
			if(hasInvalidCharacters(field.getValidation(), field.getValue())){
				e.rejectValue(field.getName(), field.getName() + ".invalid");
			}
		}
//		if(!RegexValidation.descriptionPattern.matcher(product.getDescription()).matches()){
//			e.rejectValue("description", "description.invalid");
//		}
//		if(!RegexValidation.urlPattern.matcher(product.getImageUrl()).matches()){
//			e.rejectValue("imageUrl", "imageUrl.invalid");
//		}
	}

	
}

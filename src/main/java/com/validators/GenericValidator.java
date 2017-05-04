package com.validators;

import static com.validators.RegexValidation.hasInvalidCharacters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.domain.FormField;
import com.util.ErrorProperties;

@Component
public class GenericValidator {

	@Autowired
	ErrorProperties errorProperties;
	
	public boolean isInvalidInput(FormField field){

		if(field.isRequired() && StringUtils.isEmpty(field.getValue())){
			field.setError(errorProperties.getErrorMessages().get(field.getName() + ".required"));
			return true;
		}
		
		if(field.getValidation() != null){
			if(hasInvalidCharacters(field.getValidation(), field.getValue())){
				field.setError(errorProperties.getErrorMessages().get(field.getName()+ ".invalid"));
				return true;
			}
		}
		
		return false;
		
	}
	
}

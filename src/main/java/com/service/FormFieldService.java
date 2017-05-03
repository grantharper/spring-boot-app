package com.service;

import com.domain.FormField;

public interface FormFieldService {

	Iterable<FormField> listAllFormFields();
	
	void saveAllFormFields(Iterable<FormField> formFields);
}

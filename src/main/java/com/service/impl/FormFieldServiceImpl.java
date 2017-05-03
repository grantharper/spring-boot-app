package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.domain.FormField;
import com.repository.FormFieldRepository;
import com.service.FormFieldService;

@Service
public class FormFieldServiceImpl implements FormFieldService{

	@Resource
	FormFieldRepository formFieldRespository; 
	
	@Override
	public Iterable<FormField> listAllFormFields() {
		return formFieldRespository.findAll();
	}

	@Override
	public void saveAllFormFields(Iterable<FormField> formFields) {

		for(FormField field: formFields){
			formFieldRespository.save(field);
		}
		
	}

}

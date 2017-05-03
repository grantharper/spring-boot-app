package com.controllers;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.domain.FormField;
import com.service.FormFieldService;
import com.validators.FormFieldValidator;

@Controller
public class GenericController {

	private Logger log = Logger.getLogger(GenericController.class);

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
		
		// this approach does not work. The formFields are not captured as a
		// list, likely because there is not a one to one match with their
		// instance variable names
		// for (FormField field : formFields) {
		// log.info(field.toString());
		// }
		
		Map<String, String[]> requestMap = request.getParameterMap();
		

		//choice - store the validation information on the html, or contact the DB for the validation info before running the validation
		
		//get keys from DB based on names
		Iterable<FormField> formFields = formFieldService.listAllFormFields();
		for(FormField field: formFields){
			for (Map.Entry<String, String[]> entry : requestMap.entrySet()) {
				String key = entry.getKey();
				String[] values = entry.getValue();
				String valueOut = "";
				for (String value : values) {
					valueOut += value;
				}
				log.info(key + "=" + valueOut);
				if(field.getName().equals(key)){
					field.setValue(valueOut);
				}
			}
		}
		formFieldService.saveAllFormFields(formFields);

		// update the database with the new values of the form elements

		// formFieldValidator.validate(obj, e);

		return "redirect:/generic-form";
	}

}

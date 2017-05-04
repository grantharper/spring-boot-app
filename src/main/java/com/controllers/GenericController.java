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
import com.validators.GenericValidator;

@Controller
public class GenericController {

	private Logger log = Logger.getLogger(GenericController.class);

	@Resource
	FormFieldService formFieldService;
	@Autowired
	private GenericValidator genericValidator;

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

		Iterable<FormField> formFields = formFieldService.listAllFormFields();
		if (hasInvalidUserInput(request, formFields)) {
			model.addAttribute("formFields", formFields);
			return "generic-form";
		}

		formFieldService.saveAllFormFields(formFields);

		return "redirect:/generic-form";
	}

	private boolean hasInvalidUserInput(HttpServletRequest request, Iterable<FormField> formFields) {

		// choice - store the validation information on the html, or contact the
		// DB for the validation info before running the validation

		// get keys from DB based on names

		Map<String, String[]> requestMap = request.getParameterMap();

		boolean hasErrors = false;
		for (FormField field : formFields) {
			if (requestMap.containsKey(field.getName())) {
				String[] values = requestMap.get(field.getName());
				String valueOut = "";
				for (String value : values) {
					valueOut += value;
				}
				log.info(field.getName() + "=" + valueOut);
				field.setValue(valueOut);
				// perform validation
				boolean invalid = genericValidator.isInvalidInput(field);
				if (!hasErrors && invalid) {
					hasErrors = true;
				}
			}
		}

		return hasErrors;
	}
}

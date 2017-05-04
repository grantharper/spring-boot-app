package com.domain;

import java.util.regex.Pattern;

import javax.persistence.Entity;

@Entity
public class FormField extends AbstractDomainClass {
	
	private String label;
	private String name;
	private String value;
	private boolean required;
	private String validation;
	private String error;
	
	public boolean isInvalidInput(String input){
		return Pattern.compile(this.validation).matcher(input).matches();
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public String getValidation() {
		return validation;
	}
	public void setValidation(String validation) {
		this.validation = validation;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "FormField [label=" + label + ", name=" + name + ", value=" + value + ", required=" + required
				+ ", validation=" + validation + ", error=" + error + "]";
	}
	
	

}

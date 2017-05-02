package com.validators;

import java.util.regex.Pattern;

public class RegexValidation {

	
	public static final Pattern descriptionPattern = Pattern.compile("^[A-Za-z0-9\\s]*$");
	
	public static final Pattern urlPattern = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
	
	
}

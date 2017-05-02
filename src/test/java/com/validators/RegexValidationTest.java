package com.validators;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexValidationTest {

	@Test
	public void testDescription() {
		String valid = "This is size 10 shirt";
		
		String invalid = "This - I think";
		
		assertTrue(RegexValidation.descriptionPattern.matcher(valid).matches());
		
		assertFalse(RegexValidation.descriptionPattern.matcher(invalid).matches());
	}

}

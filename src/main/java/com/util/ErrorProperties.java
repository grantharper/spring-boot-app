package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class ErrorProperties {
	
	private Map<String, String> errorMessages = new HashMap<String, String>();
	
	Properties prop = new Properties();
	InputStream input = null;

	public ErrorProperties(){
		try {
			
			String filename = "error.properties";
			input = getClass().getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			prop.load(input);

			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				System.out.println("Key : " + key + ", Value : " + value);
				errorMessages.put(key, value);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

	
	
}

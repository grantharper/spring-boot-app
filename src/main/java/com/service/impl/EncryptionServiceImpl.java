package com.service.impl;

import javax.annotation.Resource;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

import com.service.EncryptionService;

@Service
public class EncryptionServiceImpl implements EncryptionService {

	@Resource
	StrongPasswordEncryptor strongEncryptor;
	
	@Override
	public String encryptString(String input) {
		return strongEncryptor.encryptPassword(input);
	}

	@Override
	public boolean checkPassword(String plainPassword, String encryptedPassword) {
		return strongEncryptor.checkPassword(plainPassword, encryptedPassword);
	}

}

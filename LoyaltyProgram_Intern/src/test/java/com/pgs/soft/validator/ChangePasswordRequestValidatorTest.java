package com.pgs.soft.validator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pgs.soft.dto.ChangePasswordRequestDTO;

public class ChangePasswordRequestValidatorTest {

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	ChangePasswordRequestValidator changePasswordRequestValidator = new ChangePasswordRequestValidator();

	@Test
	public void supportsTest() {
		boolean result = changePasswordRequestValidator.supports(ChangePasswordRequestDTO.class);
		assertTrue(result);
	}

}

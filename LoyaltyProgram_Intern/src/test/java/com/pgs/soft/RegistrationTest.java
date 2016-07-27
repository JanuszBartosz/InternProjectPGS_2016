package com.pgs.soft;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.validation.BindException;

import com.pgs.soft.controller.UserController;
import com.pgs.soft.dto.UserDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { LoyaltyProgramInternApplication.class,
		MvcConfig.class, SecurityConfig.class })
public class RegistrationTest {

	@Autowired
	UserController userController;

	@Test
	public void registerTest() {
		// Given
		String email = "test@test.com";
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(email);
		BindException bindingException = new BindException(userDTO, "userDTO");

		// When
		userController.register(userDTO, bindingException);

		// Then
		assertFalse(bindingException.hasErrors());

	}

}

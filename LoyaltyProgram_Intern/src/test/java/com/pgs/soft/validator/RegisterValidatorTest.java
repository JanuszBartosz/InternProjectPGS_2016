package com.pgs.soft.validator;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import com.pgs.soft.domain.User;
import com.pgs.soft.dto.UserDTO;
import com.pgs.soft.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RegisterValidatorTest {

	@Mock
	private UserServiceImpl userServiceMock;

	@InjectMocks
	private RegisterValidator registerValidator = new RegisterValidator();

	@Mock
	private Errors errors;

	@Test
	public void supportsTest() throws Exception {
		boolean supports = registerValidator.supports(UserDTO.class);
		assertTrue(supports);
	}

	@Test
	public void validateTest() throws Exception {
		// Given
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail("adrian@gmail.com");
		userDTO.setPassword("pass");
		userDTO.setPasswordRepeated("pass");
		Optional<User> optionalEmptyUser = Optional.ofNullable(null);
		Mockito.when(userServiceMock.getUserByEmail(userDTO.getEmail())).thenReturn(optionalEmptyUser);

		// When
		registerValidator.validate(userDTO, errors);

		// Then
		verify(errors, never()).rejectValue(anyString(), anyString());
	}
}

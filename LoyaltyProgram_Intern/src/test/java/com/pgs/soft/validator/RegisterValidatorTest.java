package com.pgs.soft.validator;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
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

	UserDTO userDTO;

	@Before
	public void setUp() {
		userDTO = new UserDTO();
	}

	@Test
	public void supportsTest() throws Exception {
		boolean supports = registerValidator.supports(UserDTO.class);
		assertTrue(supports);
	}

	@Test
	public void testValidateSuccess() throws Exception {
		// Given
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

	@Test
	public void testValidateUserExists() throws Exception {
		// Given
		userDTO.setEmail("adrian@gmail.com");
		userDTO.setPassword("pass");
		userDTO.setPasswordRepeated("pass");
		Optional<User> optionalUser = Optional.ofNullable(new User());
		Mockito.when(userServiceMock.getUserByEmail(userDTO.getEmail())).thenReturn(optionalUser);

		// When
		registerValidator.validate(userDTO, errors);

		// Then
		verify(errors).rejectValue("email", "email.exists");
	}

	@Test
	public void testValidateIncorrectEmail() throws Exception {
		// Given
		userDTO.setEmail("adriangmail.co321m");
		userDTO.setPassword("pass");
		userDTO.setPasswordRepeated("pass");
		Optional<User> optionalUser = Optional.ofNullable(null);
		Mockito.when(userServiceMock.getUserByEmail(userDTO.getEmail())).thenReturn(optionalUser);

		// When
		registerValidator.validate(userDTO, errors);

		// Then
		verify(errors).rejectValue("email", "email.incorrect");
	}

	@Test
	public void testValidatePasswordsDontMatch() throws Exception {
		// Given
		userDTO.setEmail("adrian@gmail.com");
		userDTO.setPassword("pass");
		userDTO.setPasswordRepeated("diffpass");
		Optional<User> optionalUser = Optional.ofNullable(null);
		Mockito.when(userServiceMock.getUserByEmail(userDTO.getEmail())).thenReturn(optionalUser);

		// When
		registerValidator.validate(userDTO, errors);

		// Then
		verify(errors).rejectValue("passwordRepeated", "passwords.nomatch");
	}
}

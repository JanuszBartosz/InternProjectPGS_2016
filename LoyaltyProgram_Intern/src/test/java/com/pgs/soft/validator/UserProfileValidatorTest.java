package com.pgs.soft.validator;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import com.pgs.soft.dto.UserProfileDTO;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileValidatorTest {

	UserProfileValidator userProfileValidator = new UserProfileValidator();

	@Mock
	Errors errors;

	UserProfileDTO userProfileDTO;

	@Before
	public void setUp() {
		userProfileDTO = new UserProfileDTO();
	}

	@Test
	public void supportsTest() throws Exception {
		boolean supports = userProfileValidator.supports(UserProfileDTO.class);
		assertTrue(supports);
	}

	@Test
	public void testValidateSuccess() throws Exception {

		// Given
		userProfileDTO.setName("Adrian");
		userProfileDTO.setSurname("Kulinski");

		// When
		userProfileValidator.validate(userProfileDTO, errors);

		// Then
		verify(errors, never()).rejectValue(anyString(), anyString());
	}

	@Test
	public void testValidateIncorrectName() throws Exception {

		// Given
		userProfileDTO.setName("Adr321ian");
		userProfileDTO.setSurname("Kulinski");

		// When
		userProfileValidator.validate(userProfileDTO, errors);

		// Then
		verify(errors).rejectValue("name", "name.incorrect");
	}

	@Test
	public void testValidateIncorrectSurname() throws Exception {

		// Given
		userProfileDTO.setName("Adrian");
		userProfileDTO.setSurname("Kulin321ski");

		// When
		userProfileValidator.validate(userProfileDTO, errors);

		// Then
		verify(errors).rejectValue("surname", "surname.incorrect");
	}

}

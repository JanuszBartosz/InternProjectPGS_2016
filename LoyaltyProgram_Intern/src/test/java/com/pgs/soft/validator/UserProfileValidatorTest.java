package com.pgs.soft.validator;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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

	@Test
	public void supportsTest() throws Exception {
		boolean supports = userProfileValidator.supports(UserProfileDTO.class);
		assertTrue(supports);
	}

	@Test
	public void validateTest() throws Exception {

		// Given
		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO.setName("Adrian");
		userProfileDTO.setSurname("Kulinski");

		// When
		userProfileValidator.validate(userProfileDTO, errors);

		// Then
		verify(errors, never()).rejectValue(anyString(), anyString());
	}

}

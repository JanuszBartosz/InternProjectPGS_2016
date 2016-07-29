package com.pgs.soft.validator;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;

import com.pgs.soft.domain.User;
import com.pgs.soft.dto.ChangePasswordRequestDTO;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordRequestValidatorTest {

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@InjectMocks
	ChangePasswordRequestValidator changePasswordRequestValidator = new ChangePasswordRequestValidator();

	@Mock
	Errors errors;

	@Test
	public void supportsTest() {
		boolean result = changePasswordRequestValidator.supports(ChangePasswordRequestDTO.class);
		assertTrue(result);
	}

	@Test
	public void validationCorrectTest() {
		// Given
		changePasswordRequestValidator.setbCryptPasswordEncoder(bCryptPasswordEncoder);
		String newPassword = "newPassword";
		String newPasswordRepeat = "newPassword";
		String oldPassword = "oldPassword";
		ChangePasswordRequestDTO changePasswordRequestDTO = new ChangePasswordRequestDTO(oldPassword, newPassword,
				newPasswordRepeat);

		User loggedUser = new User();
		loggedUser.setPassword(bCryptPasswordEncoder.encode(oldPassword));
		Authentication auth = new UsernamePasswordAuthenticationToken(loggedUser, null);
		SecurityContextHolder.getContext().setAuthentication(auth);

		// When
		changePasswordRequestValidator.validate(changePasswordRequestDTO, errors);

		// Then
		verify(errors, never()).rejectValue(anyString(), anyString());
	}

	@Test
	public void repeatPasswordNotSameTest() {

		// Given
		changePasswordRequestValidator.setbCryptPasswordEncoder(bCryptPasswordEncoder);
		String newPassword = "newPassword";
		String newPasswordRepeat = "newPasswordDifferent";
		String oldPassword = "oldPassword";
		ChangePasswordRequestDTO changePasswordRequestDTO = new ChangePasswordRequestDTO(oldPassword, newPassword,
				newPasswordRepeat);

		User loggedUser = new User();
		loggedUser.setPassword(bCryptPasswordEncoder.encode(oldPassword));
		Authentication auth = new UsernamePasswordAuthenticationToken(loggedUser, null);
		SecurityContextHolder.getContext().setAuthentication(auth);

		// When
		changePasswordRequestValidator.validate(changePasswordRequestDTO, errors);

		// Then
		verify(errors, times(1)).rejectValue("newPasswordRepeat", "passwords.nomatch");
	}

	@Test
	public void oldPasswordEmptyTest() {

		// Given
		changePasswordRequestValidator.setbCryptPasswordEncoder(bCryptPasswordEncoder);
		String newPassword = "newPassword";
		String newPasswordRepeat = "newPasswordDifferent";
		String oldPassword = "";
		ChangePasswordRequestDTO changePasswordRequestDTO = new ChangePasswordRequestDTO(oldPassword, newPassword,
				newPasswordRepeat);

		User loggedUser = new User();
		loggedUser.setPassword(bCryptPasswordEncoder.encode(oldPassword));
		Authentication auth = new UsernamePasswordAuthenticationToken(loggedUser, null);
		SecurityContextHolder.getContext().setAuthentication(auth);

		// When
		changePasswordRequestValidator.validate(changePasswordRequestDTO, errors);

		// Then
		verify(errors, times(1)).reject("old.password.empty", "Old password is empty!");
	}

	@Test
	public void newPasswordEmptyTest() {

		// Given
		changePasswordRequestValidator.setbCryptPasswordEncoder(bCryptPasswordEncoder);
		String newPassword = "";
		String newPasswordRepeat = "newPassword";
		String oldPassword = "oldPassword";
		ChangePasswordRequestDTO changePasswordRequestDTO = new ChangePasswordRequestDTO(oldPassword, newPassword,
				newPasswordRepeat);

		User loggedUser = new User();
		loggedUser.setPassword(bCryptPasswordEncoder.encode(oldPassword));
		Authentication auth = new UsernamePasswordAuthenticationToken(loggedUser, null);
		SecurityContextHolder.getContext().setAuthentication(auth);

		// When
		changePasswordRequestValidator.validate(changePasswordRequestDTO, errors);

		// Then
		verify(errors, times(1)).reject("new.password.empty", "New password is empty!");
	}

	@Test
	public void newPasswordRepeatEmptyTest() {

		// Given
		changePasswordRequestValidator.setbCryptPasswordEncoder(bCryptPasswordEncoder);
		String newPassword = "newPassword";
		String newPasswordRepeat = "";
		String oldPassword = "oldPassword";
		ChangePasswordRequestDTO changePasswordRequestDTO = new ChangePasswordRequestDTO(oldPassword, newPassword,
				newPasswordRepeat);

		User loggedUser = new User();
		loggedUser.setPassword(bCryptPasswordEncoder.encode(oldPassword));
		Authentication auth = new UsernamePasswordAuthenticationToken(loggedUser, null);
		SecurityContextHolder.getContext().setAuthentication(auth);

		// When
		changePasswordRequestValidator.validate(changePasswordRequestDTO, errors);

		// Then
		verify(errors, times(1)).reject("repeat.password.empty", "Repeat password is empty!");
	}

	@Test
	public void oldPasswordIncorrectTest() {

		// Given
		changePasswordRequestValidator.setbCryptPasswordEncoder(bCryptPasswordEncoder);
		String newPassword = "newPassword";
		String newPasswordRepeat = "newPassword";
		String oldPassword = "oldPassword";
		String oldPasswordDiff = "oldPasswordDiff";
		ChangePasswordRequestDTO changePasswordRequestDTO = new ChangePasswordRequestDTO(oldPassword, newPassword,
				newPasswordRepeat);

		User loggedUser = new User();
		loggedUser.setPassword(bCryptPasswordEncoder.encode(oldPasswordDiff));
		Authentication auth = new UsernamePasswordAuthenticationToken(loggedUser, null);
		SecurityContextHolder.getContext().setAuthentication(auth);

		// When
		changePasswordRequestValidator.validate(changePasswordRequestDTO, errors);

		// Then
		verify(errors, times(1)).rejectValue("oldPassword", "password.incorrect");
	}

	@Test
	public void newPasswordSameAsOldTest() {

		// Given
		changePasswordRequestValidator.setbCryptPasswordEncoder(bCryptPasswordEncoder);
		String newPassword = "oldPassword";
		String newPasswordRepeat = "newPassword";
		String oldPassword = "oldPassword";
		ChangePasswordRequestDTO changePasswordRequestDTO = new ChangePasswordRequestDTO(oldPassword, newPassword,
				newPasswordRepeat);

		User loggedUser = new User();
		loggedUser.setPassword(bCryptPasswordEncoder.encode(oldPassword));
		Authentication auth = new UsernamePasswordAuthenticationToken(loggedUser, null);
		SecurityContextHolder.getContext().setAuthentication(auth);

		// When
		changePasswordRequestValidator.validate(changePasswordRequestDTO, errors);

		// Then
		verify(errors, times(1)).rejectValue("newPassword", "passwords.same");
	}
}

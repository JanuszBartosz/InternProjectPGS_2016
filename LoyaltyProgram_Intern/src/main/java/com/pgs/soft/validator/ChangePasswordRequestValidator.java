package com.pgs.soft.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.domain.User;
import com.pgs.soft.dto.ChangePasswordRequestDTO;

@Component
public class ChangePasswordRequestValidator implements Validator {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ChangePasswordRequestDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ChangePasswordRequestDTO passwordDTO = (ChangePasswordRequestDTO) target;
		validatePassword(errors, passwordDTO);

	}

	private void validatePassword(Errors errors, ChangePasswordRequestDTO passwordDTO) {

		checkIfBlank(passwordDTO.getOldPassword(), errors, "old.password.empty", "Old password is empty!");
		checkIfBlank(passwordDTO.getNewPassword(), errors, "new.password.empty", "New password is empty!");
		checkIfBlank(passwordDTO.getNewPasswordRepeat(), errors, "repeat.password.empty", "Repeat password is empty!");

		if (!StringUtils.equals(passwordDTO.getNewPassword(), passwordDTO.getNewPasswordRepeat()))
			errors.rejectValue("newPasswordRepeat", "passwords.nomatch");

		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String oldPasswordHash = loggedUser.getPassword();

		if (!bCryptPasswordEncoder.matches(passwordDTO.getOldPassword(), oldPasswordHash))
			errors.rejectValue("oldPassword", "password.incorrect");

		if (bCryptPasswordEncoder.matches(passwordDTO.getNewPassword(), oldPasswordHash))
			errors.rejectValue("newPassword", "passwords.same");

	}

	private void checkIfBlank(String string, Errors errors, String errorKey, String errorMessage) {
		if (StringUtils.isBlank(string))
			errors.reject(errorKey, errorMessage);
	}
}

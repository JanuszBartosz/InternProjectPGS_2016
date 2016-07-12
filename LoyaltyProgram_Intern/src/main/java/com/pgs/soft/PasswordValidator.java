package com.pgs.soft;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.dto.PasswordDTO;

public class PasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(PasswordDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PasswordDTO passwordDTO = (PasswordDTO) target;
		validatePassword(errors, passwordDTO);
		
	}

	private void validatePassword(Errors errors, PasswordDTO passwordDTO) {
		
		if( passwordDTO.getOldPassword() != null && !passwordDTO.getOldPassword().isEmpty() )
			errors.reject("old.password.empty", "Old password is empty!");
				
		if(passwordDTO.getNewPassword() == null && passwordDTO.getNewPassword().isEmpty())
			errors.reject("new.password.empty", "New password is empty!");
		
		if(passwordDTO.getNewPasswordRepeat() == null && passwordDTO.getNewPasswordRepeat().isEmpty())
			errors.reject("repeat.password.empty", "Repeat password is empty!");
				
		if( !passwordDTO.getNewPassword().equals(passwordDTO.getNewPasswordRepeat()) )
			errors.reject("passwords.not.equal", "Password and repeated password do not match!");
		
		if(passwordDTO.getNewPassword().equals(passwordDTO.getOldPassword()))
			errors.reject("passwords.same", "New password the same as old one!");
		}
		
	

}

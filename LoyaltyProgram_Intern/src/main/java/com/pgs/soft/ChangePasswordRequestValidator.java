package com.pgs.soft;

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
		
		if( !(passwordDTO.getOldPassword() != null && !passwordDTO.getOldPassword().isEmpty()) )
			errors.reject("old.password.empty", "Old password is empty!");
				
		if( !(passwordDTO.getNewPassword() != null && !passwordDTO.getNewPassword().isEmpty()) )
			errors.reject("new.password.empty", "New password is empty!");
		
		if( !(passwordDTO.getNewPasswordRepeat() != null && !passwordDTO.getNewPasswordRepeat().isEmpty()) )
			errors.reject("repeat.password.empty", "Repeat password is empty!");
				
		if( !(passwordDTO.getNewPasswordRepeat() != null && passwordDTO.getNewPassword().equals(passwordDTO.getNewPasswordRepeat())) )
			errors.reject("passwords.not.equal", "Password and repeated password do not match!");
					
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
										
		String oldPasswordHash = loggedUser.getPassword();
		
		if(! bCryptPasswordEncoder.matches(passwordDTO.getOldPassword(), oldPasswordHash))
			errors.reject("old.password.wrong", "Wrong old password!");
		
		if(	bCryptPasswordEncoder.matches(passwordDTO.getNewPassword(), oldPasswordHash))
			errors.reject("passwords.same", "New password the same as old one!");
		
	}
		

}

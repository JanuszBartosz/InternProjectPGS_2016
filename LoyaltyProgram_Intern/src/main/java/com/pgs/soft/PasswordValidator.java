package com.pgs.soft;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.dto.PasswordDTO;
import com.pgs.soft.service.impl.UserServiceImpl;

@Component
public class PasswordValidator implements Validator {

	@Autowired
	private HttpSession session;
	
	@Autowired 
	private UserServiceImpl userService;
	
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
		
		if( !(passwordDTO.getOldPassword() != null && !passwordDTO.getOldPassword().isEmpty()) )
			errors.reject("old.password.empty", "Old password is empty!");
				
		if( !(passwordDTO.getNewPassword() != null && !passwordDTO.getNewPassword().isEmpty()) )
			errors.reject("new.password.empty", "New password is empty!");
		
		if( !(passwordDTO.getNewPasswordRepeat() != null && !passwordDTO.getNewPasswordRepeat().isEmpty()) )
			errors.reject("repeat.password.empty", "Repeat password is empty!");
				
		if( !(passwordDTO.getNewPasswordRepeat() != null && passwordDTO.getNewPassword().equals(passwordDTO.getNewPasswordRepeat())) )
			errors.reject("passwords.not.equal", "Password and repeated password do not match!");
		
		System.out.println(session.getAttribute("email") + "   " + session.getAttribute("id") + "   " + session.getAttribute("authorities"));
		
		String inputNewPasswordHash = new BCryptPasswordEncoder().encode(passwordDTO.getNewPassword());
		String inputOldPasswordHash = new BCryptPasswordEncoder().encode(passwordDTO.getOldPassword());
		String email = (String) session.getAttribute("email");
		Optional<com.pgs.soft.domain.User> user = userService.getUserByEmail(email);
		String oldPasswordHash = user.get().getPassword();
		
		if(!oldPasswordHash.equals(inputOldPasswordHash))
			errors.reject("old.password.wrong", "Wrong old password!");
		
		if(inputNewPasswordHash.equals(oldPasswordHash))
			errors.reject("passwords.same", "New password the same as old one!");
		
	}
		

}

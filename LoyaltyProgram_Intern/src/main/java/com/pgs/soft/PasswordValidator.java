package com.pgs.soft;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.domain.User;
import com.pgs.soft.dto.PasswordDTO;
import com.pgs.soft.service.UserService;

@Component
public class PasswordValidator implements Validator {

	@Autowired
	private HttpSession session;
	
	@Autowired 
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(PasswordDTO.class);
	}

	//Metoda ustawiająca parametry validatora.
	@Override
	public void validate(Object target, Errors errors) {
		PasswordDTO passwordDTO = (PasswordDTO) target;
		validatePassword(errors, passwordDTO);
		
	}
	
	//Warunki validacji.
	private void validatePassword(Errors errors, PasswordDTO passwordDTO) {
		
		if( !(passwordDTO.getOldPassword() != null && !passwordDTO.getOldPassword().isEmpty()) )
			errors.reject("old.password.empty", "Old password is empty!");
				
		if( !(passwordDTO.getNewPassword() != null && !passwordDTO.getNewPassword().isEmpty()) )
			errors.reject("new.password.empty", "New password is empty!");
		
		if( !(passwordDTO.getNewPasswordRepeat() != null && !passwordDTO.getNewPasswordRepeat().isEmpty()) )
			errors.reject("repeat.password.empty", "Repeat password is empty!");
				
		if( !(passwordDTO.getNewPasswordRepeat() != null && passwordDTO.getNewPassword().equals(passwordDTO.getNewPasswordRepeat())) )
			errors.reject("passwords.not.equal", "Password and repeated password do not match!");
					
		String email = (String) session.getAttribute("email");
							
		User user = userService.getUserByEmail(email).get();
		String oldPasswordHash = user.getPassword();
		
		if(! new BCryptPasswordEncoder().matches(passwordDTO.getOldPassword(), oldPasswordHash))
			errors.reject("old.password.wrong", "Wrong old password!");
		
		if(new BCryptPasswordEncoder().matches(passwordDTO.getNewPassword(), oldPasswordHash))
			errors.reject("passwords.same", "New password the same as old one!");
		
	}
		

}

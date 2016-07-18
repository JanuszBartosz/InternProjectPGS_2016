package com.pgs.soft;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.dto.UserDTO;
import com.pgs.soft.service.UserService;

@Component
public class RegisterValidator implements Validator {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDTO userDTO = (UserDTO) target;
		validateEmail(errors, userDTO);
		validatePassword(errors, userDTO);
	}
	
	private void validatePassword(Errors errors, UserDTO userDTO){
		if(!userDTO.getPassword().equals(userDTO.getPasswordRepeated())){
			errors.rejectValue("passwordRepeated", "passwords.nomatch");
		}
	}
	
	private void validateEmail(Errors errors, UserDTO userDTO) {
        if (userService.getUserByEmail(userDTO.getEmail()).isPresent()) {
            errors.rejectValue("email", "email.exists");
        }
        if (!isEmailFormCorrect(userDTO.getEmail())){
        	errors.rejectValue("email","email.incorrect");
        } 
    }
	
	private boolean isEmailFormCorrect(String email){
		Pattern pattern;
		Matcher matcher;
		String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
		pattern = Pattern.compile(emailPattern);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}

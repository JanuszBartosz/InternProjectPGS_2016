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
public class UserDtoValidator implements Validator {
	
	private final UserService userService;
	
	@Autowired
	public UserDtoValidator(UserService userService){
		this.userService = userService;
	}

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
			errors.reject("passwords.nomatch", "Passwords does not match");
		}
	}
	
	private void validateEmail(Errors errors, UserDTO userDTO) {
        if (userService.getUserByEmail(userDTO.getEmail()).isPresent()) {
            errors.reject("email.exists", "User with that email already exists");
        }
        if (!isEmailFormCorrect(userDTO.getEmail())){
        	errors.reject("email.incorrect", "Incorrect email");
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

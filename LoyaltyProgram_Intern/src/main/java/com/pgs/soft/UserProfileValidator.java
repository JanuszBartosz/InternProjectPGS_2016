package com.pgs.soft;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.dto.UserProfileDTO;

@Component
public class UserProfileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserProfileDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserProfileDTO userProfileDTO = (UserProfileDTO) target;
		validateName(errors, userProfileDTO);
		validateSurname(errors, userProfileDTO);
	}
	
	private void validateName(Errors errors, UserProfileDTO userProfileDTO){
		if(!isStringLetter(userProfileDTO.getName())){
			errors.reject("name.incorrect","Name is incorrect");
		}
	}
	
	private void validateSurname(Errors errors, UserProfileDTO userProfileDTO){
		if(!isStringLetter(userProfileDTO.getSurname())){
			errors.reject("surname.incorrect","Surname is incorrect");
		}
	}
	
	private boolean isStringLetter (String name){
		Pattern pattern;
		Matcher matcher;
		String namePattern = "[a-zA-Z]+";
		pattern = Pattern.compile(namePattern);
		matcher = pattern.matcher(name);
		return matcher.matches();
	}
}

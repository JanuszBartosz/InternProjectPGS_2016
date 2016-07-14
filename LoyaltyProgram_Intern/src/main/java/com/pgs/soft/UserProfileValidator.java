package com.pgs.soft;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.dto.UserProfileDTO;

@Component
public class UserProfileValidator implements Validator {

	@Autowired
	HttpSession session;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserProfileDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserProfileDTO userProfileDTO = (UserProfileDTO) target;
		String userEmail = (String) session.getAttribute("email");
		if(userEmail==null){
			errors.reject("not.logged", "You are not logged.");
		}
		validateName(errors, userProfileDTO);
		validateSurname(errors, userProfileDTO);
	}
	
	private void validateName(Errors errors, UserProfileDTO userProfileDTO){
		if(!userProfileDTO.getName().isEmpty() && !isStringLetter(userProfileDTO.getName())){
			errors.reject("name.incorrect","Name is incorrect");
		}
	}
	
	private void validateSurname(Errors errors, UserProfileDTO userProfileDTO){
		if(!userProfileDTO.getSurname().isEmpty() && !isStringLetter(userProfileDTO.getSurname())){
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

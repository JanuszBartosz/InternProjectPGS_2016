package com.pgs.soft;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
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
		validateNameAndSurname(errors, userProfileDTO);
	}

	private void validateNameAndSurname(Errors errors, UserProfileDTO userProfileDTO){
		String name = userProfileDTO.getName();
		String surname = userProfileDTO.getSurname();
		Pattern pattern;
		Matcher matcher;
		String surnamePattern = "[A-Z]{1}[a-z]+-?[a-zA-Z]+";
		String namePattern = "[A-Z]{1}[a-z]+";
		if(!StringUtils.isBlank(name) && !StringUtils.isBlank(surname)){
			pattern = Pattern.compile(namePattern);
			matcher = pattern.matcher(name);
			if(!matcher.matches()){
				errors.reject("name.incorrect","Name is incorrect");
			}
			pattern = Pattern.compile(surnamePattern);
			matcher = pattern.matcher(surname);
			if(!matcher.matches()){
				errors.reject("surname.incorrect","Surname is incorrect");
			}
		}
	}
}

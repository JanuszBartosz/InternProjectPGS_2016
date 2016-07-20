package com.pgs.soft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.dto.CardNotLoggedDTO;
import com.pgs.soft.service.UserService;

@Component
public class CardValidator implements Validator{
	
	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CardNotLoggedDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CardNotLoggedDTO cardNotLoggedDTO = (CardNotLoggedDTO) target;
		validateUser(errors, cardNotLoggedDTO);
	}
	
	private void validateUser(Errors errors, CardNotLoggedDTO cardNotLoggedDTO){
		if(!userService.getUserByEmailAndNameAndSurname(cardNotLoggedDTO.getEmail(), cardNotLoggedDTO.getName(), cardNotLoggedDTO.getSurname()).isPresent()){
			errors.reject("card.user_not_exist");
		}
	}
}

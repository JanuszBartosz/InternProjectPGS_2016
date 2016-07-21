package com.pgs.soft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.domain.User;
import com.pgs.soft.dto.CardNotLoggedDTO;
import com.pgs.soft.service.CardService;
import com.pgs.soft.service.UserService;

@Component
public class CardNotLoggedValidator implements Validator{
	
	@Autowired
	UserService userService;
	
	@Autowired
	CardService cardService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CardNotLoggedDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CardNotLoggedDTO cardNotLoggedDTO = (CardNotLoggedDTO) target;
		validateUser(errors, cardNotLoggedDTO);
		validateActivate(errors, cardNotLoggedDTO);
	}
	
	private void validateUser(Errors errors, CardNotLoggedDTO cardNotLoggedDTO){
		if(!userService.getUserByEmailAndNameAndSurname(cardNotLoggedDTO.getEmail(), cardNotLoggedDTO.getName(), cardNotLoggedDTO.getSurname()).isPresent()){
			errors.reject("card.user_not_exist");
		}
	}
	
	private void validateActivate(Errors errors, CardNotLoggedDTO cardNotLoggedDTO){
		User user = userService.getUserByEmail(cardNotLoggedDTO.getEmail()).get();
		if(cardService.hasActiveCard(user.getId())){
			errors.reject("card.has_active_card");
		}
	}
}

package com.pgs.soft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.domain.User;
import com.pgs.soft.dto.AddCardRequestDTO;
import com.pgs.soft.service.CardService;

@Component
public class CardLoggedValidator implements Validator {
	
	@Autowired
	CardService cardService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(AddCardRequestDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AddCardRequestDTO cardLoggedDTO = (AddCardRequestDTO) target;
		validateUsersActiveCards(errors, cardLoggedDTO);
	}
	
	private void validateUsersActiveCards(Errors errors, AddCardRequestDTO cardLoggedDTO){
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(cardService.hasActiveCard(loggedUser.getId())){
			errors.reject("card.has_active_card");
		}
	}
}

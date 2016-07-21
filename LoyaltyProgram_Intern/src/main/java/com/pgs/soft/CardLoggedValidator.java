package com.pgs.soft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.domain.User;
import com.pgs.soft.dto.CardLoggedDTO;
import com.pgs.soft.service.CardService;

@Component
public class CardLoggedValidator implements Validator {
	
	@Autowired
	CardService cardService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CardLoggedDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CardLoggedDTO cardLoggedDTO = (CardLoggedDTO) target;
		validateActivate(errors, cardLoggedDTO);
	}
	
	private void validateActivate(Errors errors, CardLoggedDTO cardLoggedDTO){
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(cardService.hasActiveCard(loggedUser.getId())){
			errors.reject("card.has_active_card");
		}
	}
}

package com.pgs.soft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.domain.User;
import com.pgs.soft.dto.AddCardNotLoggedRequestDTO;
import com.pgs.soft.service.CardService;
import com.pgs.soft.service.UserService;

@Component
public class CardNotLoggedValidator implements Validator {

	@Autowired
	UserService userService;

	@Autowired
	CardService cardService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(AddCardNotLoggedRequestDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AddCardNotLoggedRequestDTO cardNotLoggedDTO = (AddCardNotLoggedRequestDTO) target;
		validateUser(errors, cardNotLoggedDTO);
	}

	private void validateUser(Errors errors, AddCardNotLoggedRequestDTO cardNotLoggedDTO) {
		if (!userService.getUserByEmailAndNameAndSurname(cardNotLoggedDTO.getEmail(), cardNotLoggedDTO.getName(),
				cardNotLoggedDTO.getSurname()).isPresent()) {
			errors.reject("card.user_not_exist");
			return;
		}
		User user = userService.getUserByEmail(cardNotLoggedDTO.getEmail()).get();
		if (cardService.hasActiveCard(user.getId())) {
			errors.reject("card.has_active_card");
		}

	}
}

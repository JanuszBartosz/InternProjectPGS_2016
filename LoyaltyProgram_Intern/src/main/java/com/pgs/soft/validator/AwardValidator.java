package com.pgs.soft.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.dto.AwardRequestToValidateDTO;

@Component
public class AwardValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals((AwardRequestToValidateDTO.class));
	}

	@Override
	public void validate(Object target, Errors errors) {
		AwardRequestToValidateDTO awardRequestToValidateDTO = (AwardRequestToValidateDTO) target;
		canBuy(errors, awardRequestToValidateDTO);
	}

	private void canBuy(Errors errors, AwardRequestToValidateDTO awardRequestToValidateDTO) {
		if (awardRequestToValidateDTO.getAwardDTO().getPointsPrice() > awardRequestToValidateDTO.getUserProfileDTO()
				.getPointsSum()) {
			errors.reject("available_awards.not_enough_points");
		}
		if (awardRequestToValidateDTO.getAwardDTO().getStockAmount() <= 0) {
			errors.reject("available_awards.not_available");
		}
	}
}

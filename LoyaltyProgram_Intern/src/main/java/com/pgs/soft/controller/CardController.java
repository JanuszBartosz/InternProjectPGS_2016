package com.pgs.soft.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pgs.soft.CardLoggedValidator;
import com.pgs.soft.CardNotLoggedValidator;
import com.pgs.soft.dto.AddCardRequestDTO;
import com.pgs.soft.dto.AddCardNotLoggedRequestDTO;
import com.pgs.soft.service.CardService;

@Controller
public class CardController {

	@Autowired
	private CardService cardService;
	
	@Autowired
	private CardNotLoggedValidator cardNotLoggedValidator;
	
	@Autowired
	private CardLoggedValidator cardLoggedValidator;
	
	@InitBinder("cardNotLoggedDTO")
	public void initCardNotLoggedBinder(WebDataBinder binder){
		binder.addValidators(cardNotLoggedValidator);
	}
	
	@InitBinder("cardLoggedDTO")
	public void initCardLoggedBinder(WebDataBinder binder){
		binder.addValidators(cardLoggedValidator);
	}

	@RequestMapping(value = "/main/card", method = RequestMethod.GET)
	public String cardLoggedView(@ModelAttribute("cardLoggedDTO") AddCardRequestDTO cardLoggedDTO) {
		return "card_logged";
	}
	
	@RequestMapping(value = "/main/card", method = RequestMethod.POST)
	public String cardLogged(@Valid @ModelAttribute("cardLoggedDTO") AddCardRequestDTO cardLoggedDTO, BindingResult result) {
		if(!result.hasErrors()){
			cardService.saveForLogged(cardLoggedDTO);
		}
		return "card_logged";
	}
	
	@RequestMapping(value = "card", method = RequestMethod.GET)
	public String cardView(@ModelAttribute("cardNotLoggedDTO") AddCardNotLoggedRequestDTO cardNotLoggedDTO) {
		return "card_not_logged";
	}
	
	@RequestMapping(value = "card", method = RequestMethod.POST)
	public String card(@Valid @ModelAttribute("cardNotLoggedDTO") AddCardNotLoggedRequestDTO cardNotLoggedDTO, BindingResult result) {
		if(!result.hasErrors()){
			cardService.saveForNotLogged(cardNotLoggedDTO);
		}
		return "card_not_logged";
	}	
}

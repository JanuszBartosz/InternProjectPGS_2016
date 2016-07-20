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

import com.pgs.soft.CardValidator;
import com.pgs.soft.dto.CardLoggedDTO;
import com.pgs.soft.dto.CardNotLoggedDTO;
import com.pgs.soft.service.CardService;

@Controller
public class CardController {

	@Autowired
	private CardService cardService;
	
	@Autowired
	private CardValidator cardValidator;
	
	@InitBinder("cardNotLoggedDTO")
	public void initBinder(WebDataBinder binder){
		binder.addValidators(cardValidator);
	}

	@RequestMapping(value = "/main/card", method = RequestMethod.GET)
	public String cardLoggedView(@ModelAttribute("cardLoggedDTO") CardLoggedDTO cardLoggedDTO) {
		return "card_logged";
	}
	
	@RequestMapping(value = "/main/card", method = RequestMethod.POST)
	public String cardLogged(@ModelAttribute("cardLoggedDTO") CardLoggedDTO cardLoggedDTO) {
		cardService.saveForLogged(cardLoggedDTO);
		return "card_logged";
	}
	
	@RequestMapping(value = "card", method = RequestMethod.GET)
	public String cardView(@ModelAttribute("cardNotLoggedDTO") CardNotLoggedDTO cardNotLoggedDTO) {
		return "card_not_logged";
	}
	
	@RequestMapping(value = "card", method = RequestMethod.POST)
	public String card(@Valid @ModelAttribute("cardNotLoggedDTO") CardNotLoggedDTO cardNotLoggedDTO, BindingResult result) {
		if(!result.hasErrors()){
			cardService.saveForNotLogged(cardNotLoggedDTO);
		}
		return "card_not_logged";
	}	
}

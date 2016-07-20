package com.pgs.soft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pgs.soft.dto.CardDTO;
import com.pgs.soft.service.CardService;

@Controller
public class CardController {

	@Autowired
	private CardService cardService;

	@RequestMapping(value = "/card", method = RequestMethod.GET)
	public String cardView(@ModelAttribute("cardDTO") CardDTO cardDTO) {
		return "card";
	}
	
	@RequestMapping(value = "/card", method = RequestMethod.POST)
	public String card(@ModelAttribute("cardDTO") CardDTO cardDTO) {
		cardService.save(cardDTO);
		return "card";
	}
}

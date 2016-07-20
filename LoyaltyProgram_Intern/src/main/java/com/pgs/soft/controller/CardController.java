package com.pgs.soft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgs.soft.dto.CardDTO;
import com.pgs.soft.service.CardService;

@Controller
@ResponseBody
public class CardController {
	
	@Autowired
	private CardService cardService;

	@RequestMapping(value = "/card", method = RequestMethod.POST)
	public String card(@RequestBody CardDTO cardDTO){
		
		return "done";
	}
}

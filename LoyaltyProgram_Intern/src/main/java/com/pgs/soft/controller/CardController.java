package com.pgs.soft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CardController {

	@RequestMapping(value = "/card", method = RequestMethod.POST)
	public String card(){
		return "";
	}
}

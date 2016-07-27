package com.pgs.soft.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.pgs.soft.dto.AwardDTO;
import com.pgs.soft.dto.Category;

@Controller
public class AwardsController {

	@RequestMapping(value = "/available_awards", method = RequestMethod.GET)
	public ModelAndView awardsView() {
		ModelAndView model = new ModelAndView("available_awards");

		RestTemplate restTemplate = new RestTemplate();
		Map<Category, List<AwardDTO>> mapa = new HashMap<>();// =
																// restTemplate.getForObject("http://localhost:8080/awards",
																// HashMap.class);

		List<AwardDTO> list1 = new ArrayList<>();
		list1.add(new AwardDTO());
		list1.add(new AwardDTO());
		mapa.put(Category.Books, list1);

		model.addObject("map", mapa);
		return model;
	}

}

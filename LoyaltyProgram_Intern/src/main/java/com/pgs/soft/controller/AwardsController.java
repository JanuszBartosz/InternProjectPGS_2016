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
		Map<Category, List<AwardDTO>> map = new HashMap<>();// =
															// restTemplate.getForObject("http://localhost:8080/awards",
															// HashMap.class);

		List<AwardDTO> list1 = new ArrayList<>();
		list1.add(new AwardDTO("Name1", "Description1", 1000, Category.Books));
		list1.add(new AwardDTO("Name2", "Description2", 2000, Category.Books));

		List<AwardDTO> list2 = new ArrayList<>();
		list2.add(new AwardDTO("Name3", "Description3", 3000, Category.Toys));
		list2.add(new AwardDTO("Name4", "Description4", 4000, Category.Toys));

		map.put(Category.Books, list1);
		map.put(Category.Toys, list2);

		model.addObject("map", map);
		return model;
	}

}

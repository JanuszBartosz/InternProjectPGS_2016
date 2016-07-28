package com.pgs.soft.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.pgs.soft.dto.AwardDTO;
import com.pgs.soft.dto.Category;

@Controller
public class AwardsController {

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/available_awards", method = RequestMethod.GET)
	public ModelAndView awardsView(
			@RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortProperty,
			@RequestParam(value = "direction", defaultValue = "ASC", required = false) Sort.Direction direction,
			@RequestParam(value = "category", required = false) Category category) {

		ModelAndView model = new ModelAndView("available_awards");
		RestTemplate restTemplate = new RestTemplate();
		Map<Category, List<AwardDTO>> map = new HashMap<>();

		if (category != null) {
			map = restTemplate.getForObject("http://localhost:9000/awards?category=" + category + "&sortBy="
					+ sortProperty + "&direction=" + direction, Map.class);
		}
		else {
			map = restTemplate.getForObject(
					"http://localhost:9000/awards?sortBy=" + sortProperty + "&direction=" + direction, Map.class);
		}

		model.addObject("map", map);
		return model;
	}

}

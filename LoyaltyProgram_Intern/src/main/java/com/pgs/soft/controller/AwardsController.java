package com.pgs.soft.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.pgs.soft.dto.AwardDTO;
import com.pgs.soft.dto.AwardRequestToValidateDTO;
import com.pgs.soft.dto.Category;
import com.pgs.soft.dto.UserProfileDTO;
import com.pgs.soft.service.UserProfileService;
import com.pgs.soft.validator.AwardValidator;

@Controller
public class AwardsController {

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private AwardValidator awardValidator;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/available_awards", method = RequestMethod.GET)
	public ModelAndView awardsView(
			@RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortProperty,
			@RequestParam(value = "direction", defaultValue = "ASC", required = false) Sort.Direction direction,
			@RequestParam(value = "category", required = false) Category category) {

		return new ModelAndView("available_awards", "map", getAvailableAwards(category, sortProperty, direction));
	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView orderFormView(@RequestParam(value = "id") Integer id) {

		ModelAndView model = new ModelAndView("order_form");

		AwardDTO awardDTO = restTemplate.getForObject("http://localhost:9000/get_award?id=" + id, AwardDTO.class);

		UserProfileDTO userProfileDTO = userProfileService.getUserProfile();

		model.addObject(userProfileDTO);
		model.addObject(awardDTO);

		AwardRequestToValidateDTO awardRequestToValidateDTO = new AwardRequestToValidateDTO(awardDTO, userProfileDTO);

		DataBinder binder = new DataBinder(awardRequestToValidateDTO);
		binder.setValidator(awardValidator);
		binder.validate();

		Category category = null;
		String sortProperty = "name";
		Sort.Direction direction = Direction.ASC;

		if (binder.getBindingResult().hasErrors()) {
			ModelAndView mav = new ModelAndView("available_awards", "map",
					getAvailableAwards(category, sortProperty, direction));
			mav.addObject("message", binder.getBindingResult().getAllErrors().get(0).getDefaultMessage());
			return mav;
		}

		return model;
	}

	@SuppressWarnings("unchecked")
	public Map<Category, List<AwardDTO>> getAvailableAwards(Category category, String sortProperty,
			Sort.Direction direction) {

		if (category != null) {
			return restTemplate.getForObject("http://localhost:9000/awards?category=" + category + "&sortBy="
					+ sortProperty + "&direction=" + direction, Map.class);
		}
		return restTemplate.getForObject(
				"http://localhost:9000/awards?sortBy=" + sortProperty + "&direction=" + direction, Map.class);
	}

}

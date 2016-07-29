package com.pgs.soft.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.pgs.soft.dto.AwardDTO;
import com.pgs.soft.dto.AwardRequestToValidateDTO;
import com.pgs.soft.dto.Category;
import com.pgs.soft.dto.OrderDTO;
import com.pgs.soft.dto.UserProfileDTO;
import com.pgs.soft.service.UserProfileService;
import com.pgs.soft.service.impl.OrderServiceImpl;
import com.pgs.soft.validator.AwardValidator;

@Controller
public class AwardsController {

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private AwardValidator awardValidator;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${awards_microservice.address}")
	private String awardsMicroserviceAddress;

	@Autowired
	OrderServiceImpl orderService;

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

		AwardDTO awardDTO = restTemplate.getForObject(awardsMicroserviceAddress + "get_award?id=" + id, AwardDTO.class);

		UserProfileDTO userProfileDTO = userProfileService.getUserProfile();

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setName(userProfileDTO.getName());
		orderDTO.setSurname(userProfileDTO.getSurname());
		orderDTO.setCity(userProfileDTO.getCity());
		orderDTO.setStreet(userProfileDTO.getStreet());
		orderDTO.setHomeNumber(userProfileDTO.getHomeNumber());
		orderDTO.setPostCode(userProfileDTO.getPostCode());
		orderDTO.setAwardName(awardDTO.getName());
		orderDTO.setDescription(awardDTO.getDescription());
		orderDTO.setCategory(awardDTO.getCategory().toString());
		orderDTO.setPointsPrice(String.valueOf(awardDTO.getPointsPrice()));

		model.addObject(orderDTO);

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

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String orderForm(@ModelAttribute("orderDTO") OrderDTO orderDTO) {
		orderService.saveOrUpdate(orderDTO);

		return "main";
	}

	@SuppressWarnings("unchecked")
	public Map<Category, List<AwardDTO>> getAvailableAwards(Category category, String sortProperty,
			Sort.Direction direction) {

		if (category != null) {
			return restTemplate.getForObject(awardsMicroserviceAddress + "awards?category=" + category + "&sortBy="
					+ sortProperty + "&direction=" + direction, Map.class);
		}
		return restTemplate.getForObject(
				awardsMicroserviceAddress + "awards?sortBy=" + sortProperty + "&direction=" + direction, Map.class);
	}

}

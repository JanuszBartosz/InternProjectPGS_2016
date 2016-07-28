package com.pgs.soft;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgs.soft.domain.Category;
import com.pgs.soft.dto.AwardDTO;
import com.pgs.soft.repository.AwardsRepository;
import com.pgs.soft.service.AwardsService;

@Controller
@ResponseBody
public class AwardsController {

	@Autowired
	AwardsService awardsService;

	@Autowired
	AwardsRepository awardsRepository;

	@RequestMapping(value = "/awards")
	public Map<Category, List<AwardDTO>> getAllAwardsSorted(
			@RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortProperty,
			@RequestParam(value = "direction", defaultValue = "ASC", required = false) Sort.Direction direction,
			@RequestParam(value = "category", required = false) Category category) {

		Sort sort = new Sort(direction, sortProperty);

		if (category != null) {
			Map<Category, List<AwardDTO>> map = new HashMap<>();
			map.put(category, awardsService.getAwardsByCategoryAndSorted(category, sort));
			return map;
		}
		return awardsService.getAllAwardsGrouped(sort);
	}

	@RequestMapping(value = "/get_award")
	public AwardDTO getAward(@RequestParam Integer id) {
		return awardsService.getAward(id);
	}

}

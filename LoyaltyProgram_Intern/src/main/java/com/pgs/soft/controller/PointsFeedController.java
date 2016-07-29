package com.pgs.soft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pgs.soft.dto.NewPointsRequestDTO;
import com.pgs.soft.service.PointsService;

@RestController
public class PointsFeedController {

	@Autowired
	PointsService pointsService;

	@ResponseBody
	@RequestMapping(value = "/new_points", method = RequestMethod.POST)
	public String doPost(@RequestBody NewPointsRequestDTO newPointsRequestDTO) {
		System.out.println(newPointsRequestDTO);
		pointsService.updateUserPoints(newPointsRequestDTO);
		return "200";
	}

}

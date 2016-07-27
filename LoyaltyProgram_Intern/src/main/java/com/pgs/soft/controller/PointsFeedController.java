package com.pgs.soft.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pgs.soft.dto.MessageDTO;

@RestController
public class PointsFeedController {

	@ResponseBody
	@RequestMapping(value = "/new_points", method = RequestMethod.POST)
	public String doPost(@RequestBody MessageDTO messageDTO) {
		System.out.println(messageDTO);
		return "200";
	}

}

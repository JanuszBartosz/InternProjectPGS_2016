package com.pgs.soft.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgs.soft.dto.UserProfileDTO;
import com.pgs.soft.service.UserProfileService;

@Controller
@ResponseBody
public class UserController {
	
	UserProfileService userProfileService;
	
	@Autowired
	public UserController(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}



	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String fillProfile(@RequestBody UserProfileDTO userProfileDTO){
		userProfileService.fill(userProfileDTO);
		return "Profile filled.";
	}
}

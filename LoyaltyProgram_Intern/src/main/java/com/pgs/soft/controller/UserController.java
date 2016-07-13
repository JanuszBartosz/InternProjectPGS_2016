package com.pgs.soft.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgs.soft.UserProfileValidator;
import com.pgs.soft.dto.UserProfileDTO;
import com.pgs.soft.service.UserProfileService;

@Controller
@ResponseBody
public class UserController {
	
	UserProfileService userProfileService;
	
	UserProfileValidator userProfileValidator;
	
	@Autowired
	public UserController(UserProfileService userProfileService, UserProfileValidator userProfileValidator) {
		this.userProfileService = userProfileService;
		this.userProfileValidator = userProfileValidator;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(userProfileValidator);
	}


	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String fillProfile(@RequestBody @Valid UserProfileDTO userProfileDTO, HttpServletRequest httpServletRequest){
		HttpSession session = httpServletRequest.getSession();
		String email = (String) session.getAttribute("email");
		userProfileService.fill(userProfileDTO, email);
		return "Profile filled.";
	}
}

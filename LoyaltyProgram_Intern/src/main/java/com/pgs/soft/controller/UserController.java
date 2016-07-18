package com.pgs.soft.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pgs.soft.ChangePasswordRequestValidator;
import com.pgs.soft.RegisterValidator;
import com.pgs.soft.UserProfileValidator;
import com.pgs.soft.dto.ChangePasswordRequestDTO;
import com.pgs.soft.dto.UserDTO;
import com.pgs.soft.dto.UserProfileDTO;
import com.pgs.soft.service.UserProfileService;
import com.pgs.soft.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserProfileValidator userProfileValidator;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RegisterValidator registerValidator;	
	
	@Autowired
	ChangePasswordRequestValidator passwordValidator;
	
	@InitBinder("userProfileDTO")
	public void initProfileBinder(WebDataBinder binder){
		binder.addValidators(userProfileValidator);
	}
	
	@InitBinder("userDTO")
	public void initRegisterBinder(WebDataBinder binder){
		binder.addValidators(registerValidator);
	}
	
	@InitBinder("passwordDTO")
    public void initChangePasswordBinder(WebDataBinder binder) {
        binder.addValidators(passwordValidator);
    }

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String fillProfile(@Valid @RequestBody UserProfileDTO userProfileDTO){
		userProfileService.save(userProfileDTO);
		return "Profile filled.";
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.GET)
	public String registerView(@ModelAttribute("userDTO") UserDTO userDTO){
		return "register";
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result){
		if(result.hasErrors()){
			return "register";
		}
		userService.save(userDTO);
		return "register";
	}

	@RequestMapping(value = "/change_password", method=RequestMethod.POST)
	public String changePassword(@Valid @RequestBody ChangePasswordRequestDTO passwordDTO){
		
		userService.changePassword(passwordDTO);
			
		return "Password changed successfully.";

	}
}

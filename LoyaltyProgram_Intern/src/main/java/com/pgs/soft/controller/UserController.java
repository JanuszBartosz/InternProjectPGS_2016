package com.pgs.soft.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgs.soft.UserDtoValidator;
import com.pgs.soft.dto.UserDTO;
import com.pgs.soft.service.UserService;

@Controller
@ResponseBody
public class UserController {
	
	UserService userService;
	UserDtoValidator userDtoValidator;
	
	@Autowired
	public UserController(UserService userService, UserDtoValidator userDtoValidator){
		this.userService = userService;
		this.userDtoValidator = userDtoValidator;
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userDtoValidator);
    }
	
	@RequestMapping(value = "/user", method=RequestMethod.POST)
	public String register(@Valid @RequestBody UserDTO userDTO){
		userService.register(userDTO);
		return "Registered.";
	}
}

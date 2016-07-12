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

import com.pgs.soft.PasswordValidator;
import com.pgs.soft.dto.PasswordDTO;
import com.pgs.soft.service.impl.UserServiceImpl;

@Controller
@ResponseBody
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	PasswordValidator passwordValidator;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(passwordValidator);
    }
	
	@RequestMapping(value = "/change_password", method=RequestMethod.POST)
	String changePassword(@Valid @RequestBody PasswordDTO passwordDTO){
		
		userService.changePassword(passwordDTO.getNewPassword());
		
		return "Password changed succesfully.";
	}

}

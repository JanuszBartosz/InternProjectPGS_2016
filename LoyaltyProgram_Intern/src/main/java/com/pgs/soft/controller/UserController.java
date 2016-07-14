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
import com.pgs.soft.service.UserService;

@Controller
@ResponseBody
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordValidator passwordValidator;
	
	//Dodanie validatora.
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(passwordValidator);
    }
	
	//Metoda obsługująca zmianę hasła.
	@RequestMapping(value = "/change_password", method=RequestMethod.POST)
	public String changePassword(@Valid @RequestBody PasswordDTO passwordDTO){
					
		return "Password changed successfully.";
	}

}

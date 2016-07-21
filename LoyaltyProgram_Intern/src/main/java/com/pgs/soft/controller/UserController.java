package com.pgs.soft.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pgs.soft.ChangePasswordRequestValidator;
import com.pgs.soft.RegisterValidator;
import com.pgs.soft.UserProfileValidator;
import com.pgs.soft.domain.User;
import com.pgs.soft.dto.ChangePasswordRequestDTO;
import com.pgs.soft.dto.LoginFormDTO;
import com.pgs.soft.dto.UserDTO;
import com.pgs.soft.dto.UserProfileDTO;
import com.pgs.soft.service.HobbyService;
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
	
	@Autowired
	HobbyService hobbyService;
	
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
	
	@ModelAttribute("hobbiesNames")
	public Set<String> getHobbiesNames()
	{ 
		return hobbyService.getAllHobbiesNames();
	}
	
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public ModelAndView fillProfileView(){
		ModelAndView model = new ModelAndView("profile");
		model.addObject(userProfileService.getUserProfile());	
		return model;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String fillProfile(@Valid @ModelAttribute("userProfileDTO") UserProfileDTO userProfileDTO, BindingResult result){
		if(!result.hasErrors()){
			userProfileService.save(userProfileDTO);
		}
		return "profile";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String getLogin(@ModelAttribute ("loginForm") LoginFormDTO loginForm){
		return "login";
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.GET)
	public String registerView(@ModelAttribute("userDTO") UserDTO userDTO){
		return "register";
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result){
		if(!result.hasErrors()){
			userService.register(userDTO);
			return "index";
		}
		else
			return "register";
	}
	
	@RequestMapping(value="/activate_account", method=RequestMethod.GET)
	public ModelAndView activateAccount(@RequestParam String uuid){
		if(userService.checkUUID(uuid))			
			return new ModelAndView("index", "message", "Confirmation succeeded, you can now log in.");
		else
			return new ModelAndView("index", "message", "Confirmation failed, account cannot be activated!");
	}
	
	@RequestMapping(value = "/change_password", method=RequestMethod.GET)
	public String changePasswordView(@ModelAttribute("passwordDTO") ChangePasswordRequestDTO passwordDTO){
		return "change_password";
	}

	@RequestMapping(value = "/change_password", method=RequestMethod.POST)
	public String changePassword(@Valid @ModelAttribute("passwordDTO") ChangePasswordRequestDTO passwordDTO, BindingResult result){
		if(!result.hasErrors()){
			userService.changePassword(passwordDTO);
			return "main";
		}				
		return "change_password";
	}
	
	@RequestMapping("/main")
	public ModelAndView mainPage(){
		ModelAndView model = new ModelAndView("main");
		model.addObject("loggedUser", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return model;
	}
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
}

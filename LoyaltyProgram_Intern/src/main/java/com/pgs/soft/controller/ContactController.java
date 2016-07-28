package com.pgs.soft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pgs.soft.domain.User;
import com.pgs.soft.dto.ContactMessageDTO;
import com.pgs.soft.service.ContactService;

@Controller
public class ContactController {

	@Autowired
	ContactService contactService;

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView doGet(@ModelAttribute ContactMessageDTO contactMessageDTO) {

		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			contactMessageDTO.setEmail(loggedUser.getEmail());
		}

		return new ModelAndView("contact_form");

	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public ModelAndView doPost(@ModelAttribute ContactMessageDTO contactMessageDTO) {
		contactService.saveAndSendEmail(contactMessageDTO);

		return new ModelAndView("index", "message", "Email sent successfully.");
	}

}

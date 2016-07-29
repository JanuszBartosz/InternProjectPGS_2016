package com.pgs.soft.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {

		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		SecurityContextHolderAwareRequestWrapper securityContextHolderAwareRequestWrapper = new SecurityContextHolderAwareRequestWrapper(
				httpServletRequest, "");

		if (securityContextHolderAwareRequestWrapper.isUserInRole("USER"))
			httpServletResponse.sendRedirect("/main");
		else if (securityContextHolderAwareRequestWrapper.isUserInRole("REGISTERED"))
			httpServletResponse.sendRedirect("/change_password");

	}
}

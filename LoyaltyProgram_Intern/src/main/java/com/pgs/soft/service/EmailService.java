package com.pgs.soft.service;

import java.util.List;

import org.apache.http.NameValuePair;

public interface EmailService {

	void sendRegisterConfirmationEmail(String to, String email, String registrationToken);

	void sendContactConfirmationEmail(String to, String subject, String message);

	void sendData(List<NameValuePair> urlParameters, String templateName);

}
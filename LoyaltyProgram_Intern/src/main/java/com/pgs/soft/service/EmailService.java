package com.pgs.soft.service;

import java.util.List;

import org.apache.http.NameValuePair;

public interface EmailService {

	void sendConfirmationEmail(String to, String email, String randomPassword, String registrationToken);

	void sendContactConfirmationEmail(String to, String subject, String message);

	void sendData(List<NameValuePair> urlParameters, String templateName);

}
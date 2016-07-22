package com.pgs.soft.service;

import java.util.List;

import org.apache.http.NameValuePair;

public interface EmailService {

	void sendConfirmationEmail(String to, String email, String registrationToken);

	void sendData(List<NameValuePair> urlParameters);

}
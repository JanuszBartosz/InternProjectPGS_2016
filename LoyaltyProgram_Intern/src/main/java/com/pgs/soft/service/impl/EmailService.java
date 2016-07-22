package com.pgs.soft.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@PropertySource("email.properties")
@Service
public class EmailService {
    
	@Value("${url}")
	private String emailUrl;
	@Value("${user.name}")
	private String userName;
	@Value("${api.key}")
	private String apiKey;
	@Value("${email.template.for.registration}")
	private String template;
	
	public  void sendConfirmationEmail(String to, String email, String registrationToken)  {
		
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("userName", userName));
        urlParameters.add(new BasicNameValuePair("api_key", apiKey));
        urlParameters.add(new BasicNameValuePair("template", template));
        urlParameters.add(new BasicNameValuePair("to", to));
        urlParameters.add(new BasicNameValuePair("merge_email", email));
        urlParameters.add(new BasicNameValuePair("merge_token", registrationToken));

        sendData(urlParameters);
    }

	private void sendData(List<NameValuePair> urlParameters)
	{
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(emailUrl);
		

		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			client.execute(post);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			      
	}
	
}

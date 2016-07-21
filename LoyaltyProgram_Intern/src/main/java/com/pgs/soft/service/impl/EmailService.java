package com.pgs.soft.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

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
	
	public  String sendConfirmationEmail(String to, String email, String registrationToken)  {
        
		String data = "";
		
	    try{
	        data = "userName=" + URLEncoder.encode(userName, "UTF-8");
	        data += "&api_key=" + URLEncoder.encode(apiKey, "UTF-8");
	        data += "&template=" + URLEncoder.encode(template, "UTF-8");
	        data += "&to=" + URLEncoder.encode(to, "UTF-8");
	        data += "&merge_email=" + URLEncoder.encode(email, "UTF-8");
	        data += "&merge_token=" + URLEncoder.encode(registrationToken, "UTF-8");
	    }
	    catch(Exception e){
            e.printStackTrace();
	    }
        return sendData(data);
    }

	private String sendData(String data)
	{
		String result = "";
		try {
	        URL url = new URL(emailUrl);
	        URLConnection conn = url.openConnection();
	        conn.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        wr.write(data);
	        wr.flush();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));            
	        result = rd.readLine();
	        wr.close();
	        rd.close();
		}
        catch(Exception e) {
            
            e.printStackTrace();
        }
		
        return result;
	}
	
}

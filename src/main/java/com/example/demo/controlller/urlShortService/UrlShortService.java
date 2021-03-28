package com.example.demo.controlller.urlShortService;


import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

@Service
public class UrlShortService {
	
	public String generateRandomUrl(String longUrl) {
		try {
			URI uri = new URI(longUrl);
			String randomUrl =  getAlphaNumericString(7);
			//System.out.println(randomUrl);
			return randomUrl;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getAlphaNumericString(int n){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
  
        return sb.toString();
    }
}

package com.example.demo.controlller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controlller.urlShortService.UrlShortService;

import entity.Redirect;
import entity.ShortUrl;

@RestController
public class UrlShortnerController {

	// short url, full url
	Map<String, String> map = new HashMap<>();
	@Autowired
	private UrlShortService urlShortService;

	
	  @PostMapping("/generate") 
	  public ResponseEntity<?> createShortUrl(@RequestBody Redirect redirectUrl) {
		  String shortUrl = urlShortService.generateRandomUrl(redirectUrl.getUrl());
		  map.put(shortUrl, redirectUrl.getUrl());
		  
		  return new ResponseEntity<>(new ShortUrl(map.size() + 1L, shortUrl, new Date()), HttpStatus.OK);
	  }
	 

	@GetMapping("/{url}")
	public ResponseEntity<?> handleRequest(@PathVariable String url) throws URISyntaxException {
		String fullUrl = map.get(url);
		System.out.println("short url: " + url + " long url: " + fullUrl);
		URI uri = new URI(fullUrl);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
	}
}

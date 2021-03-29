package com.example.demo.controlller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controlller.repository.UrlRepo;
import com.example.demo.controlller.urlShortService.UrlShortService;
import com.example.demo.entity.Redirect;
import com.example.demo.entity.ShortUrl;
import com.example.demo.exception.InvalidUrlException;

@RestController
public class UrlShortnerController {

	// short url, full url
	Map<String, String> map = new HashMap<>();
	@Autowired
	private UrlShortService urlShortService;
	
	@Autowired
	private UrlRepo repo;

	  @CrossOrigin
	  @PostMapping("/generate") 
	  public ResponseEntity<?> createShortUrl(@RequestBody Redirect redirectUrl) {
		  String fullUrl = redirectUrl.getUrl();
		  // checking if same website was already present in database if so, return the old url
		  List<ShortUrl> shortUrls = repo.findByFullUrl(fullUrl);
		  if(!shortUrls.isEmpty()) {
			  return new ResponseEntity<>(shortUrls.get(0), HttpStatus.OK);
		  }
		  // Assuming this service always generate random urls
		  String shortUrl = urlShortService.generateRandomUrl(redirectUrl.getUrl());
		  ShortUrl shoUrl = new ShortUrl();
		  shoUrl.setDate(new Date());
		  shoUrl.setFullUrl(fullUrl);
		  shoUrl.setUrl(shortUrl);
		  repo.save(shoUrl);
		  //map.put(shortUrl, redirectUrl.getUrl());
		  
		  return new ResponseEntity<>(shoUrl, HttpStatus.OK);
	  }
	 
	@CrossOrigin
	@GetMapping("/{url}")
	public ResponseEntity<?> handleRequest(@PathVariable String url) throws URISyntaxException, InvalidUrlException {
		//String fullUrl = "https://www.google.com";
		List<ShortUrl> shortUrls = repo.findByUrl(url);
		if(shortUrls != null && !shortUrls.isEmpty()) {
			//System.out.println("ShortUrls: " + shortUrls);
			ShortUrl redirect = shortUrls.get(0);
			String fullUrl = redirect.getFullUrl();
			System.out.println("short url: " + url + " long url: " + fullUrl);
			URI uri = new URI(fullUrl);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uri);
			return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
		}else {
			throw new InvalidUrlException();
		}
	}
}

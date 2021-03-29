package com.example.demo.controlller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ShortUrl;

@Repository
public interface UrlRepo extends CrudRepository<ShortUrl, Long>{
	public List<ShortUrl> findByUrl(String url);
	
	public List<ShortUrl> findByFullUrl(String fullUrl);
}

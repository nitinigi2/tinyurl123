package entity;

import java.util.Date;

public class ShortUrl {
	
	private Long id;
	private String url;
	private Date date;
	
	
	public ShortUrl(Long id, String url, Date date) {
		super();
		this.id = id;
		this.url = url;
		this.date = date;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}

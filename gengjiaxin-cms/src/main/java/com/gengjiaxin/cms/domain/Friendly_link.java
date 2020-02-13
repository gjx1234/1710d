package com.gengjiaxin.cms.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Friendly_link {

	private Integer id;
	
	private String text;
	
	private String url;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Friendly_link(Integer id, String text, String url, Date created) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.created = created;
	}

	public Friendly_link() {
		super();
	}
	
	
}

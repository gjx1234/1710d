package com.gengjiaxin.month.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Drivers implements Serializable{

	private Integer id;
	
	private String title;
	
	private String video;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	private Types type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Drivers [id=" + id + ", title=" + title + ", video=" + video + ", type=" + type + "]";
	}
	
	
}

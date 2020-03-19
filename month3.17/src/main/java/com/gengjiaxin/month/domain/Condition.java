package com.gengjiaxin.month.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Condition  implements Serializable{
	
	private Integer tid;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date1;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date2;
	
	private String title;

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Condition [tid=" + tid + ", date1=" + date1 + ", date2=" + date2 + ", title=" + title + "]";
	}
	
	
	
}

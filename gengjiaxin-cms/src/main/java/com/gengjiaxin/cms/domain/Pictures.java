package com.gengjiaxin.cms.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Pictures implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private String url;
	
	private String desc;
	

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public Pictures() {
		super();
	}
	
	
}

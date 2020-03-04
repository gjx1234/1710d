package com.gengjiaxin.cms.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Article implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String title;
	
	private String content;
	
	private String picture;
	
	private Integer channel_id;
	
	private Integer category_id;
	
	private User user;
	
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	private Integer hits;
	
	private Integer hot;
	
	private Integer status;
	
	private Integer deleted;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updated;
	
	private Integer readCount;
	
	private List<Pictures> pictures;
	
	
	public Integer getReadCount() {
		return readCount;
	}
	public List<Pictures> getPictures() {
		return pictures;
	}
	public void setPictures(List<Pictures> pictures) {
		this.pictures = pictures;
	}
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(Integer channel_id) {
		this.channel_id = channel_id;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Article(Integer id, String title, String content, String picture, Integer channel_id, Integer category_id,
			User user, Integer userId, Integer hits, Integer hot, Integer status, Integer deleted, Date created,
			Date updated, Integer readCount) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.picture = picture;
		this.channel_id = channel_id;
		this.category_id = category_id;
		this.user = user;
		this.userId = userId;
		this.hits = hits;
		this.hot = hot;
		this.status = status;
		this.deleted = deleted;
		this.created = created;
		this.updated = updated;
		this.readCount = readCount;
	}
	public Article() {
		super();
	}
	
	
}

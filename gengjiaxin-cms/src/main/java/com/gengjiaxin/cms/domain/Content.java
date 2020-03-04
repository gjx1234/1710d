package com.gengjiaxin.cms.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Content {

	private Integer id;
	
	private User user;
	
	private Article article;
	
	private String content;
	
	private Integer article_id;
	
	private Integer user_id;
	
	public Integer getArticle_id() {
		return article_id;
	}

	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Content(Integer id, User user, Article article, String content, Date created) {
		super();
		this.id = id;
		this.user = user;
		this.article = article;
		this.content = content;
		this.created = created;
	}

	public Content() {
		super();
	}

	public Content(Integer id, String content, Integer article_id, Integer user_id) {
		super();
		this.id = id;
		this.content = content;
		this.article_id = article_id;
		this.user_id = user_id;
	}

	
	public Content(Integer id, String content, Integer article_id, Integer user_id, Date created) {
		super();
		this.id = id;
		this.content = content;
		this.article_id = article_id;
		this.user_id = user_id;
		this.created = created;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", user=" + user + ", article=" + article + ", content=" + content
				+ ", article_id=" + article_id + ", user_id=" + user_id + ", created=" + created + "]";
	}
	
	
}

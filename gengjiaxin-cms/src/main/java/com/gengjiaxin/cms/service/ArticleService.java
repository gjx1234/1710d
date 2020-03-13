package com.gengjiaxin.cms.service;

import java.util.List;

import com.gengjiaxin.cms.domain.Article;
import com.gengjiaxin.cms.domain.Channel;
import com.gengjiaxin.cms.domain.Content;
import com.github.pagehelper.PageInfo;

public interface ArticleService {

	public PageInfo<Article> selectsByAdmin(Article article,Integer pageNum,Integer pageSize);

	public PageInfo<Article> selectArticles(Article article,Integer pageNum,Integer pageSize);
	
	public boolean update(Article article);

	public Article select(int id);

	public List<Channel> selectsChannel();

	public List<Channel> selectsCategory(int id);

	public void add(Article article);

	public PageInfo<Content> selectContents(Integer aid,Integer pageNum, Integer pageSize);

	public List<Article> selectArticesContected(Article article1);

	public List<Integer> getIds();

	public void updateHit(Article article1);
	
}

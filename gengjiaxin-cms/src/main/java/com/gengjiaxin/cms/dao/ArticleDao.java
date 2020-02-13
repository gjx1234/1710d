package com.gengjiaxin.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gengjiaxin.cms.domain.Article;
import com.gengjiaxin.cms.domain.Channel;
import com.gengjiaxin.cms.domain.Content;

public interface ArticleDao {

	public List<Article> selectByAdmin(Article article);
	
	public List<Article> selectArticles(Article article);

	public int update(Article article);

	public Article select(int id);

	public List<Channel> selectsChannel();

	public List<Channel> selectsCategory(int id);

	public void add(Article article);

	public List<Content> selectContents(Integer aid);

	public int addReadCount(Article article);

	public List<Article> selectArticesContected(Article article1);

	public List<Integer> getIds();
	
}

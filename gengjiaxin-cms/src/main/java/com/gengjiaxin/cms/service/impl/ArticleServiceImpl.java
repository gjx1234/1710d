package com.gengjiaxin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gengjiaxin.cms.dao.ArticleDao;
import com.gengjiaxin.cms.domain.Article;
import com.gengjiaxin.cms.domain.Channel;
import com.gengjiaxin.cms.domain.Content;
import com.gengjiaxin.cms.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	@Override
	public PageInfo<Article> selectsByAdmin(Article article, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<Article> list = articleDao.selectByAdmin(article);
		return new PageInfo<Article>(list);
	}

	@Override
	public boolean update(Article article) {
		int result = articleDao.update(article);
		if(result>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Article select(int id) {
		return articleDao.select(id);
	}

	@Override
	public List<Channel> selectsChannel() {
		return articleDao.selectsChannel();
	}

	@Override
	public List<Channel> selectsCategory(int id) {
		return articleDao.selectsCategory(id);
	}

	@Override
	public void add(Article article) {
		articleDao.add(article);
	}

	@Override
	public PageInfo<Article> selectArticles(Article article, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<Article> list = articleDao.selectArticles(article);
		System.out.println(article.getUserId());
		return new PageInfo<Article>(list);
	}

	@Override
	public PageInfo<Content> selectContents(Integer aid,Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		 List<Content> contents = articleDao.selectContents(aid);
		return new PageInfo<Content>(contents);
	}

	@Override
	public List<Article> selectArticesContected(Article article1) {
		return articleDao.selectArticesContected(article1);
	}

	@Override
	public List<Integer> getIds() {
		return articleDao.getIds();
	}

	@Override
	public void updateHit(Article article1) {
		articleDao.updateHit(article1);
	}

}

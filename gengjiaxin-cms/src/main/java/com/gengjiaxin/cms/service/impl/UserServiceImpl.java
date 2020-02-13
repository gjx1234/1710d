package com.gengjiaxin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gengjiaxin.cms.dao.ArticleDao;
import com.gengjiaxin.cms.dao.UserDao;
import com.gengjiaxin.cms.domain.Article;
import com.gengjiaxin.cms.domain.Content;
import com.gengjiaxin.cms.domain.User;
import com.gengjiaxin.cms.service.UserService;
import com.gengjiaxin.cms.vo.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ArticleDao articleDao;

	@Override
	public PageInfo<User> getUserList(String username, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<User> list = userDao.getUserList(username);
		return new PageInfo<User>(list);
	}

	@Override
	public int updateLocked(User user) {
		return userDao.updateLocked(user);
	}

	@Override
	public User getOne(Integer id) {
		return userDao.getOne(id);
	}

	@Override
	public int getCountByUserName(String username) {
		return userDao.getCountByUserName(username);
	}

	@Override
	public void addUser(UserVO user) {
		userDao.addUser(user);
	}

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public int advice(Content content) {
		Integer id = content.getArticle().getId();
		Article article = articleDao.select(id);
		article.setReadCount(article.getReadCount()+1);
		int i = userDao.advice(content);
		if(i>0) {
			i = articleDao.addReadCount(article);
		}
		return i;
	}

	@Override
	public List<Integer> getIds() {
		return userDao.getIds();
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}
	

}

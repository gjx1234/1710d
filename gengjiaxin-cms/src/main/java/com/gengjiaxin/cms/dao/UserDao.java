package com.gengjiaxin.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gengjiaxin.cms.domain.Article;
import com.gengjiaxin.cms.domain.Content;
import com.gengjiaxin.cms.domain.User;
import com.gengjiaxin.cms.vo.UserVO;

public interface UserDao {

	public List<User> getUserList(@Param("username")String username);
	
	public int updateLocked(User user);
	
	public User getOne(Integer id);

	public int getCountByUserName(String username);

	public void addUser(UserVO user);

	public User login(User user);
	
	public Article getOneArticle(Integer id);

	public int advice(Content content);

	public List<Integer> getIds();

	public int updateUser(User user);
}

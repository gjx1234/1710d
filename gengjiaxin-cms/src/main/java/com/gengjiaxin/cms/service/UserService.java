package com.gengjiaxin.cms.service;

import java.util.List;

import com.gengjiaxin.cms.domain.Content;
import com.gengjiaxin.cms.domain.User;
import com.gengjiaxin.cms.vo.UserVO;
import com.github.pagehelper.PageInfo;

public interface UserService {

	public PageInfo<User> getUserList(String username,Integer pageNum,Integer pageSize);
	
	public int updateLocked(User user);
	
	public User getOne(Integer id);

	public int getCountByUserName(String username);

	public void addUser(UserVO user);

	public User login(User user);

	public int advice(Content content);

	public List<Integer> getIds();

	public int updateUser(User user);
}

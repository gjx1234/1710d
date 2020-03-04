package com.gengjiaxin.cms.dao;

import java.util.List;

import com.gengjiaxin.cms.domain.Collections;
import com.gengjiaxin.cms.domain.User;

public interface CollectionDao {

	public List<Collections> collections(User user);
	
	public Integer addCollection(Collections collection);
	
	public Integer deleteCollection(Collections collection);
}

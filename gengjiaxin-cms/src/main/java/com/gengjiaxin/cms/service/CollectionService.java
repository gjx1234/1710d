package com.gengjiaxin.cms.service;

import com.gengjiaxin.cms.domain.Collections;
import com.gengjiaxin.cms.domain.User;
import com.github.pagehelper.PageInfo;

public interface CollectionService {

	public PageInfo<Collections> collections(User user,Integer pageNum,Integer pageSize);

	public Integer addCollection(Collections collection);

	public Integer deleteCollection(Collections collection);
}

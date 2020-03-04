package com.gengjiaxin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bw.utils.StringUtil;
import com.gengjiaxin.cms.dao.CollectionDao;
import com.gengjiaxin.cms.domain.Collections;
import com.gengjiaxin.cms.domain.User;
import com.gengjiaxin.cms.service.CollectionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CollectionServiceImpl implements CollectionService {

	@Autowired
	private CollectionDao collectionDao;
	
	@Override
	public PageInfo<Collections> collections(User user,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Collections> collections = collectionDao.collections(user);
		return new PageInfo<Collections>(collections);
	}

	@Override
	public Integer addCollection(Collections collection) {
		if(StringUtil.isHttpUrl(collection.getUrl())) {
			return collectionDao.addCollection(collection);
		}else {
			System.out.println("文本地址不合法！");
			return 0;
		}
	}

	@Override
	public Integer deleteCollection(Collections collection) {
		return collectionDao.deleteCollection(collection);
	}

}

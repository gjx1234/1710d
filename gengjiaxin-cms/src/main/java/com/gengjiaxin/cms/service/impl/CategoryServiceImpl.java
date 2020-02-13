package com.gengjiaxin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gengjiaxin.cms.dao.CategoryDao;
import com.gengjiaxin.cms.domain.Category;
import com.gengjiaxin.cms.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> selects(Integer channel_id) {
		return categoryDao.selectCategorys(channel_id);
	}
	
}

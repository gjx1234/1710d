package com.gengjiaxin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gengjiaxin.cms.dao.ContentDao;
import com.gengjiaxin.cms.domain.Content;
import com.gengjiaxin.cms.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private ContentDao contentDao;

	@Override
	public int getRandomContents(List<Content> contents) {
		return contentDao.getRandomContents(contents);
	}
	
	
}

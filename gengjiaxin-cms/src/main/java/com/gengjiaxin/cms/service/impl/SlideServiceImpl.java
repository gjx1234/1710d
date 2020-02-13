package com.gengjiaxin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gengjiaxin.cms.dao.SlideDao;
import com.gengjiaxin.cms.domain.Slide;
import com.gengjiaxin.cms.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService{

	@Autowired
	private SlideDao slideDao;
	@Override
	public List<Slide> selectSlides() {
		return slideDao.selectSlides();
	}
	
}

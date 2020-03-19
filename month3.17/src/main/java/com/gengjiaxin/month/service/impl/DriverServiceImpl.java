package com.gengjiaxin.month.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gengjiaxin.month.dao.DriverDao;
import com.gengjiaxin.month.domain.Condition;
import com.gengjiaxin.month.domain.Drivers;
import com.gengjiaxin.month.domain.Types;
import com.gengjiaxin.month.service.DriverService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class DriverServiceImpl implements DriverService{
	
	@Autowired
	private DriverDao dao;

	@Override
	public PageInfo<Drivers> list(Condition con, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Drivers> list = dao.list(con);
		return new PageInfo<Drivers>(list);
	}

	@Override
	public Integer addDriver(Drivers driver) {
		return dao.addDriver(driver);
	}

	@Override
	public Integer checkDriver(Drivers driver) {
		return dao.checkDriver(driver);
	}

	@Override
	public List<Types> typeList() {
		return dao.typeList();
	}

	@Override
	public Drivers selectOne(Drivers driver) {
		return dao.selectOne(driver);
	}

}


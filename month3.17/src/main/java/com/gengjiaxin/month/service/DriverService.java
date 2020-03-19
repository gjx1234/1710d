package com.gengjiaxin.month.service;

import java.util.List;

import com.gengjiaxin.month.domain.Condition;
import com.gengjiaxin.month.domain.Drivers;
import com.gengjiaxin.month.domain.Types;
import com.github.pagehelper.PageInfo;

public interface DriverService {

	PageInfo<Drivers> list(Condition con,Integer pageNum,Integer pageSize);

	Integer addDriver(Drivers driver);

	Integer checkDriver(Drivers driver);

	List<Types> typeList();

	Drivers selectOne(Drivers driver);
}

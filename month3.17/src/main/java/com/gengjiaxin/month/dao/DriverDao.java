package com.gengjiaxin.month.dao;

import java.util.List;

import com.gengjiaxin.month.domain.Condition;
import com.gengjiaxin.month.domain.Drivers;
import com.gengjiaxin.month.domain.Types;

public interface DriverDao {

	List<Drivers> list (Condition con);
	
	Integer addDriver(Drivers driver);
	
	Integer checkDriver(Drivers driver);
	
	Types selectType(Integer id);
	
	List<Types> typeList();

	Drivers selectOne(Drivers driver);
}

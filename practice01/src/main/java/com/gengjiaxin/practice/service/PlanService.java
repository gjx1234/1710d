package com.gengjiaxin.practice.service;

import java.util.List;
import java.util.Set;

import com.gengjiaxin.practice.domain.Department;
import com.gengjiaxin.practice.domain.Plan;
import com.github.pagehelper.PageInfo;

public interface PlanService {

	void addDepartment(Set<String> departments);

	Integer selectDept(String deptName);

	void addPlan(List<Plan> plans);
	
	PageInfo<Plan> selectPlans(Integer pageNum, Integer pageSize, String planName);

	Plan selectOne(Integer id);

	List<Department> selectDepts();

	int add(Plan plan);

	int update(Plan plan);

	boolean batchDelete(String ids);

}

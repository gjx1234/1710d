package com.gengjiaxin.practice.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.gengjiaxin.practice.domain.Department;
import com.gengjiaxin.practice.domain.Plan;

public interface PlanDao {

	void addDepartments(@Param("departments")Set<String> departments);

	Integer selectDept(String deptName);

	void addPlan(@Param("plans")List<Plan> plans);
	
	public List<Plan> selectPlans(@Param("name")String name);

	Plan selectOne(Integer id);

	List<Department> selectDepts();

	int add(Plan plan);

	Plan selectOnePlan(Integer id);

	int update(Plan plan);

	boolean batchDelete(@Param("ids")String ids);

}

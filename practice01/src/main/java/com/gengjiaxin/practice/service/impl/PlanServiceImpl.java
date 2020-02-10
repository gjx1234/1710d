package com.gengjiaxin.practice.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gengjiaxin.practice.dao.PlanDao;
import com.gengjiaxin.practice.domain.Department;
import com.gengjiaxin.practice.domain.Plan;
import com.gengjiaxin.practice.service.PlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanDao dao;

	@Override
	public void addDepartment(Set<String> departments) {
		dao.addDepartments(departments);
	}

	@Override
	public Integer selectDept(String deptName) {
		return dao.selectDept(deptName);
	}

	@Override
	public void addPlan(List<Plan> plans) {
		dao.addPlan(plans);
	}

	@Override
	public PageInfo<Plan> selectPlans(Integer pageNum, Integer pageSize, String planName) {
		PageHelper.startPage(pageNum,pageSize);
		List<Plan> plans = dao.selectPlans(planName);
		return new PageInfo<Plan>(plans);
	}

	@Override
	public Plan selectOne(Integer id) {
		return dao.selectOne(id);
	}

	@Override
	public List<Department> selectDepts() {
		return dao.selectDepts();
	}

	@Override
	public int add(Plan plan) {
		return dao.add(plan);
	}

	@Override
	public int update(Plan plan) {
		return dao.update(plan);
	}

	@Override
	public boolean batchDelete(String ids) {
		return dao.batchDelete(ids);
	}

}

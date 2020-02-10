package com.gengjiaxin.practice.service.impl;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bw.utils.StreamUtil;
import com.gengjiaxin.practice.domain.Plan;
import com.gengjiaxin.practice.service.PlanService;
import com.github.pagehelper.util.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class PlanServiceImplTest {

	@Autowired
	private PlanService service;
	@Test
	public void testAddDepartment() {
		InputStream inputStream = PlanServiceImplTest.class.getResourceAsStream("/plan.txt");
		List<String> list = StreamUtil.readLine(inputStream);
		Set<String> departments = new HashSet<String>();
		for (String string : list) {
			String[] line = string.split("\\|\\|");
			if(StringUtil.isNotEmpty(line[4])) {
				departments.add(line[4]);
			}
		}
		service.addDepartment(departments);
	}
	
	@Test
	public void testAddPlan() {
		InputStream inputStream = PlanServiceImplTest.class.getResourceAsStream("/plan.txt");
		List<String> list = StreamUtil.readLine(inputStream);
		List<Plan> plans = new ArrayList();
		for (String string : list) {
			String[] line = string.split("\\|\\|");
			Plan plan = new Plan();
			if(StringUtil.isNotEmpty(line[0])) {
				plan.setName(line[0]);
			}
			if(StringUtil.isNotEmpty(line[1])) {
				plan.setAmount(Double.parseDouble(line[1]));
			}
			if(StringUtil.isNotEmpty(line[2])) {
				plan.setContent(line[2]);
			}
			if(StringUtil.isNotEmpty(line[3])) {
				plan.setManager(line[3]);
			}
			if(StringUtil.isNotEmpty(line[4])) {
				Integer dept_id = service.selectDept(line[4]);
				System.out.println(dept_id);
				plan.setDept_id(dept_id);
			}
			plans.add(plan);
		}
		service.addPlan(plans);
	}

}

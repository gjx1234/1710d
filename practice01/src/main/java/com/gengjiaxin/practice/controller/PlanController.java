package com.gengjiaxin.practice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gengjiaxin.practice.domain.Department;
import com.gengjiaxin.practice.domain.Plan;
import com.gengjiaxin.practice.service.PlanService;
import com.github.pagehelper.PageInfo;

@Controller
public class PlanController {

	@Autowired
	private PlanService service;

	@RequestMapping("selectPlans")
	public String selectPlans(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize, String planName) {
		PageInfo<Plan> page = service.selectPlans(pageNum,pageSize,planName);
		model.addAttribute("page",page);
		model.addAttribute("planName",planName);
		model.addAttribute("list",page.getList());
		ArrayList<Integer> pages = new ArrayList<Integer>();
		for(int i=1;i<=page.getPages();i++) {
			pages.add(i);
		}
		model.addAttribute("pages",pages);
		return "list";
	}
	
	@RequestMapping("selectOne")
	public String selectOne(Model model,Integer id) {
		Plan plan = service.selectOne(id);
		model.addAttribute("plan",plan);
		return "desc";
	}
	
	@RequestMapping("toAdd")
	public String toAdd(Model model) {
		List<Department> departments = service.selectDepts();
		model.addAttribute("departments",departments);
		return "add";
		
	}
	
	@RequestMapping("add")
	public String add(Plan plan) {
		int result = service.add(plan);
		if(result>0) {
			return "redirect:selectPlans";
		}else {
			return "redirect:toAdd";
		}
	}
	
	@RequestMapping("selectOnePlan")
	public String selectOnePlan(Model model,Integer id) {
		Plan plan = service.selectOne(id);
		List<Department> departments = service.selectDepts();
		model.addAttribute("departments",departments);
		model.addAttribute("plan",plan);
		return "update";
	}
	
	
	@RequestMapping("update")
	public String update(Plan plan) {
		int result = service.update(plan);
		System.out.println(plan.getId());
		System.out.println(result);
		if(result>0) {
			return "redirect:selectPlans";
		}else {
			return "redirect:selectPlans";
		}
	}
	
	@ResponseBody
	@RequestMapping("batchDelete")
	public boolean batchDelete(String ids) {
		return service.batchDelete(ids);
	}
}

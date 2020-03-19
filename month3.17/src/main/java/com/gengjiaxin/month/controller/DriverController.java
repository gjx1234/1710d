package com.gengjiaxin.month.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.gengjiaxin.month.domain.Condition;
import com.gengjiaxin.month.domain.Drivers;
import com.gengjiaxin.month.domain.Types;
import com.gengjiaxin.month.service.DriverService;
import com.github.pagehelper.PageInfo;

@Controller
public class DriverController {

	@Autowired
	private DriverService service;
	
	@Autowired
	KafkaTemplate kafkaTemplate;

	@Autowired
	RedisTemplate redisTemplate;
	
	@RequestMapping("index")
	public String index(Condition con, Model model, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		PageInfo<Drivers> info = service.list(con, pageNum, pageSize);
		List<Types> typeList = service.typeList();
		model.addAttribute("info", info);
		model.addAttribute("list", info.getList());
		model.addAttribute("types", typeList);
		model.addAttribute("con", con);
		return "list";
	}

	@RequestMapping("checkOne")
	public String checkOne(Drivers driver,Model model) {
		Drivers d = service.selectOne(driver);
		List<Types> typeList = service.typeList();
		model.addAttribute("driver",d);
		model.addAttribute("types", typeList);
		return "check";
	}
	
	@RequestMapping("check")
	public String check(Drivers driver) {
		Integer result = service.checkDriver(driver);
		System.out.println(result);
		if(result>0) {
			return "redirect:index";
		}else {
			return "redirect:index";
		}
	}
	@RequestMapping("addOne")
	public String addOne(Model model) {
		List<Types> typeList = service.typeList();
		model.addAttribute("types", typeList);
		return "add";
	}
	
	@RequestMapping("toAdd")
	public String toAdd(Drivers driver,@RequestParam("file")MultipartFile file) {
		if(file.getSize()>0) {
			String path = "d:/pic/";
			String fileName = file.getOriginalFilename();
			String endName = fileName.substring(fileName.lastIndexOf("."));
			String name = UUID.randomUUID().toString()+endName;
			driver.setVideo(name);
			File file2 = new File(path+name);
			try {
				file.transferTo(file2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			redisTemplate.opsForValue().set("driver", JSON.toJSONString(driver));
			kafkaTemplate.send("drivers","driver");
			return "redirect:index";
		}
		return "redirect:addOne";
	}
	
}

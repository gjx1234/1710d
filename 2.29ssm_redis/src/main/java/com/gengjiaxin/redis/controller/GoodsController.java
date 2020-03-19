package com.gengjiaxin.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gengjiaxin.redis.domain.Goods;

@Controller
public class GoodsController {

	@Autowired
	RedisTemplate redisTemplate;
	
	@RequestMapping("list")
	public String findAll(Model model,@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "10")Integer pageSize) {
		List<Goods> list = redisTemplate.opsForList().range("goods", pageSize*(pageNum-1), pageSize*(pageNum-1)+(pageSize-1));
		model.addAttribute("list",list);
		model.addAttribute("pageNum",pageNum);
		return "list";
	}
}

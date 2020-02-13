package com.gengjiaxin.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gengjiaxin.cms.domain.Friendly_link;
import com.gengjiaxin.cms.service.Friendly_linkService;
import com.github.pagehelper.PageInfo;

import net.sf.jsqlparser.statement.select.First;

@Controller
@RequestMapping("link")
public class LinkController {
	@Autowired
	private Friendly_linkService linkService;

	@RequestMapping("selects")
	public String selectLinks(Model model, Friendly_link link, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		PageInfo<Friendly_link> links = linkService.selectLink(link, pageNum, pageSize);
		model.addAttribute("links", links.getList());
		model.addAttribute("info", links);
		model.addAttribute("link", link);
		return "admin/link";
	}

	@RequestMapping("toAdd")
	public String toAdd() {
		return "admin/addLink";
	}

	@RequestMapping("addLink")
	public String addLink(Friendly_link link,Model model) {
		int result = linkService.addLink(link);
		if(result>0) {
			return "redirect:/admin";
		}else {
			return "redirect:/link/selects";
		}
	}
	
	@RequestMapping("toUpdate")
	public String toUpdate(Integer id,Model model) {
		Friendly_link link = linkService.toUpdate(id);
		model.addAttribute("link",link);
		return "admin/updateLink";
	}
	
	@RequestMapping("updateLink")
	public String updateLink(Friendly_link link) {
		int result = linkService.updateLink(link);
		if(result>0) {
			return "redirect:/admin";
		}else {
			return "redirect:/link/toUpdate?id="+link.getId();
		}
	}
	
	@RequestMapping("deleteLink")
	public String deleteLink(Friendly_link link) {
		int result = linkService.deleteLink(link);
		if(result>0) {
			return "redirect:/admin";
		}else {
			return "redirect:/admin";
		}
	}
}

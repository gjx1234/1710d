package com.gengjiaxin.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gengjiaxin.cms.dao.Friendly_linkDao;
import com.gengjiaxin.cms.domain.Friendly_link;
import com.gengjiaxin.cms.service.Friendly_linkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class Friendly_linkServiceImpl implements Friendly_linkService {

	@Autowired
	private Friendly_linkDao linkdao;
	@Override
	public List<Friendly_link> selectLinkes() {
		return linkdao.selectLinkes();
	}
	@Override
	public PageInfo<Friendly_link> selectLink(Friendly_link link, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<Friendly_link> links = linkdao.selectLink(link);
		return new PageInfo(links);
	}
	@Override
	public int addLink(Friendly_link link) {
		return linkdao.addLink(link);
	}
	@Override
	public Friendly_link toUpdate(Integer id) {
		return linkdao.toUpdate(id);
	}
	@Override
	public int updateLink(Friendly_link link) {
		return linkdao.updateLink(link);
	}
	@Override
	public int deleteLink(Friendly_link link) {
		return linkdao.deleteLink(link);
	}

}

package com.gengjiaxin.cms.service;

import java.util.List;

import com.gengjiaxin.cms.domain.Friendly_link;
import com.github.pagehelper.PageInfo;

public interface Friendly_linkService {

	public List<Friendly_link> selectLinkes();

	public PageInfo<Friendly_link> selectLink(Friendly_link link, Integer pageNum, Integer pageSize);

	public int addLink(Friendly_link link);

	public Friendly_link toUpdate(Integer id);

	public int updateLink(Friendly_link link);

	public int deleteLink(Friendly_link link);
}

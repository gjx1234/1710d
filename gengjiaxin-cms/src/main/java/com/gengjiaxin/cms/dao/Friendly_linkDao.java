package com.gengjiaxin.cms.dao;

import java.util.List;

import com.gengjiaxin.cms.domain.Friendly_link;

public interface Friendly_linkDao {

	public List<Friendly_link> selectLinkes();

	public List<Friendly_link> selectLink(Friendly_link link);
	
	public int addLink(Friendly_link link);
	
	public int updateLink(Friendly_link link);
	
	public int deleteLink(Friendly_link link);

	public Friendly_link toUpdate(Integer id);
}

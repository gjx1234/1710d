package com.gengjiaxin.cms.dao;

import java.util.List;

import com.gengjiaxin.cms.domain.Content;

public interface ContentDao {

	public int getRandomContents(List<Content> contents);
}

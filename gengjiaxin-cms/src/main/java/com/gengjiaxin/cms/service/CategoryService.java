package com.gengjiaxin.cms.service;

import java.util.List;

import com.gengjiaxin.cms.domain.Category;

public interface CategoryService {

	List<Category> selects(Integer channel_id);

}

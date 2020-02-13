package com.gengjiaxin.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gengjiaxin.cms.domain.Category;

public interface CategoryDao {

	List<Category> selectCategorys(@Param("channel_id")Integer channel_id);

}

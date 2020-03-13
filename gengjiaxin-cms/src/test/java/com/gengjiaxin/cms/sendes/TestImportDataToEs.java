package com.gengjiaxin.cms.sendes;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gengjiaxin.cms.dao.ArticleDao;
import com.gengjiaxin.cms.dao.ArticleRepository;
import com.gengjiaxin.cms.domain.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestImportDataToEs {

	@Autowired
	ArticleDao articleDao;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Test
	public void testImport() {
		List<Article> list = articleDao.selectByAdmin(null);
		//存入es索引库
		articleRepository.saveAll(list);
		System.out.println("导入成功");
	}
}

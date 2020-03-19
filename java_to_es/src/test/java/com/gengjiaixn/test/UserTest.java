package com.gengjiaixn.test;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.util.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gengjiaxin.dao.UserRepository;
import com.gengjiaxin.domain.User;
import com.gengjiaxin.utils.HLUtils;
import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:es.xml")
public class UserTest {

	@Autowired
	UserRepository repository;
	
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	
	//指定库和表  在实体类汇中
	
	@Test
	public void testSave() {
		User user = new User();
		user.setId(5);
		user.setName("我的名字叫王二麻子");
		user.setAddress("上海区");
		//主要根据id来查找  如果id存在是修改  id不存在是添加
		repository.save(user);
		System.out.println("成功将User对象保存到索引库");
	}
	
	@Test
	public void testDelete() {
		repository.deleteById(2);
		System.out.println("根据Id删除成功");
	}
	
	@Test
	public void testFindAll() {
		Iterable<User> findAll = repository.findAll();
		for (User user : findAll) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testFind() {
		List<User> findByName = repository.findByName("张三");
		for (User user : findByName) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testFindByAddress() {
		List<User> findByName = repository.findByAddress("北京");
		for (User user : findByName) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testHighLight() {
		PageInfo<?> light = HLUtils.findByHighLight(elasticsearchTemplate, User.class, 1, 5, new String[] {"name"}, "id", "我的");
		List<?> list = light.getList();
		for (Object object : list) {
			System.out.println(object);
		}
	}
}

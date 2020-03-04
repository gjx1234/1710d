package com.gengjiaxin.cms.service.impl;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gengjiaxin.cms.domain.Collections;
import com.gengjiaxin.cms.domain.User;
import com.gengjiaxin.cms.service.CollectionService;
import com.github.pagehelper.PageInfo;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class CollectionServiceImplTest {

	@Autowired
	private CollectionService collectionService;
	
	@Test
	public void testCollections() {
		User user = new User();
		user.setId(1);
		PageInfo<Collections> collections = collectionService.collections(user, 1, 3);
		List<Collections> list = collections.getList();
		for (Collections collection : list) {
			System.out.println(collection.toString());
		}
	}

	@Test
	public void testAddCollection() {
		Collections collection = new Collections(null, "555", "http://123.com", 1, new Date());
		System.out.println(collectionService.addCollection(collection));
	}

	@Test
	public void testDeleteCollection() {
		Collections collection = new Collections();
		collection.setId(3);
		System.out.println(collectionService.deleteCollection(collection));
	}

}

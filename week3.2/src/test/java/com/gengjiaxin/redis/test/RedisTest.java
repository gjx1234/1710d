package com.gengjiaxin.redis.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bw.utils.RandomUtil;
import com.gengjiaxin.redis.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
@SuppressWarnings("rawtypes")
public class RedisTest {

	@Autowired
	RedisTemplate redisTemplate;
	String[] ems = new String[] { "@qq.com", "@163.com", "@sian.com", "@gmail.com", "@sohu.com", "@hotmail.com",
			"@foxmail.com" };

	@Test
	public void testJDK() {
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 100000; i++) {

			User user = new User();
			user.setId(i);
			user.setName(RandomUtil.getRandomChineseString(3));
			if (RandomUtil.getRandomNumber() % 2 == 0) {
				user.setGender("女");
			} else {
				user.setGender("男");
			}

			int[] tels = RandomUtil.getRandomNumber(9);
			String telephone = "";
			for (int j = 0; j < tels.length; j++) {
				telephone = telephone + tels[j];
			}
			user.setTelephone("13" + telephone);

			String email = RandomUtil.getRandomString(RandomUtil.getRandomNumber(3, 20))
					+ ems[RandomUtil.getRandomNumber(0, 6)];

			user.setEmail(email);
			user.setBirthday("2020.3.2");
			list.add(user);
		}
		long start = System.currentTimeMillis();
		redisTemplate.opsForList().leftPush("jdklist", list);
		long end = System.currentTimeMillis();
		System.out.println("使用jdk序列化方式存储十万条user对象");
		System.out.println("所耗时间为"+(end-start)+"毫秒");
	}
	
	@Test
	public void testJSON() {
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 100000; i++) {

			User user = new User();
			user.setId(i);
			user.setName(RandomUtil.getRandomChineseString(3));
			if (RandomUtil.getRandomNumber() % 2 == 0) {
				user.setGender("女");
			} else {
				user.setGender("男");
			}

			int[] tels = RandomUtil.getRandomNumber(9);
			String telephone = "";
			for (int j = 0; j < tels.length; j++) {
				telephone = telephone + tels[j];
			}
			user.setTelephone("13" + telephone);

			String email = RandomUtil.getRandomString(RandomUtil.getRandomNumber(3, 20))
					+ ems[RandomUtil.getRandomNumber(0, 6)];

			user.setEmail(email);
			user.setBirthday("2020.3.2");
			list.add(user);
		}
		long start = System.currentTimeMillis();
		redisTemplate.opsForList().leftPush("jsonlist", list);
		long end = System.currentTimeMillis();
		System.out.println("使用json序列化方式存储十万条user对象");
		System.out.println("所耗时间为"+(end-start)+"毫秒");
	}
	
	@Test
	public void testHASH() {
		HashMap<String,String> map = new HashMap<String,String>();
		for (int i = 0; i < 100000; i++) {

			User user = new User();
			user.setId(i);
			user.setName(RandomUtil.getRandomChineseString(3));
			if (RandomUtil.getRandomNumber() % 2 == 0) {
				user.setGender("女");
			} else {
				user.setGender("男");
			}

			int[] tels = RandomUtil.getRandomNumber(9);
			String telephone = "";
			for (int j = 0; j < tels.length; j++) {
				telephone = telephone + tels[j];
			}
			user.setTelephone("13" + telephone);

			String email = RandomUtil.getRandomString(RandomUtil.getRandomNumber(3, 20))
					+ ems[RandomUtil.getRandomNumber(0, 6)];

			user.setEmail(email);
			user.setBirthday("2020.3.2");
			map.put(user.getId()+"",user.toString());
		}
		long start = System.currentTimeMillis();
		redisTemplate.opsForHash().putAll("hashlist", map);
		long end = System.currentTimeMillis();
		System.out.println("使用hash方式存储十万条user对象");
		System.out.println("所耗时间为"+(end-start)+"毫秒");
	}
}

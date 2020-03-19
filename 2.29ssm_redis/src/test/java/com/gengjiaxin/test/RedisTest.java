package com.gengjiaxin.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bw.utils.RandomUtil;
import com.bw.utils.StreamUtil;
import com.gengjiaxin.redis.domain.Goods;
import com.gengjiaxin.redis.domain.User;

@SuppressWarnings("rawtypes")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
public class RedisTest {

	@Autowired
	RedisTemplate redisTemplate;

	String[] addrs = new String[] { "@qq.com",  "@163.com", "@sian.com", "@gmail.com", "@sohu.com", "@hotmail.com" };
	@SuppressWarnings("unchecked")
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
			int[] number = RandomUtil.getRandomNumber(9);
			String phone = "";
			for (int j = 0; j < number.length; j++) {
				phone += number[j];
			}
			phone = "13" + phone;
			user.setTelephone(phone);
			user.setEmail(RandomUtil.getRandomString(RandomUtil.getRandomNumber(3,20))+addrs[RandomUtil.getRandomNumber(0, 5)]);
			user.setBirthday("2020-2-29");
			list.add(user);
		}
		long start = System.currentTimeMillis();
		redisTemplate.opsForList().leftPushAll("users1", list.toArray());
		long end = System.currentTimeMillis();
		System.out.println("序列化方式是:jdk的方式");
		System.out.println("保存数量10万个");
		System.out.println("所耗时间:"+(end-start)+"毫秒");
	}
	
	@Test
	public void testJson() {
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
			int[] number = RandomUtil.getRandomNumber(9);
			String phone = "";
			for (int j = 0; j < number.length; j++) {
				phone += number[j];
			}
			phone = "13" + phone;
			user.setTelephone(phone);
			user.setEmail(RandomUtil.getRandomString(RandomUtil.getRandomNumber(3,20))+addrs[RandomUtil.getRandomNumber(0, 5)]);
			user.setBirthday("2020-2-29");
			list.add(user);
		}
		long start = System.currentTimeMillis();
		redisTemplate.opsForList().leftPushAll("users2", list.toArray());
		long end = System.currentTimeMillis();
		System.out.println("序列化方式是json的方式");
		System.out.println("保存数量10万个");
		System.out.println("所耗时间:"+(end-start)+"毫秒");
	}
	
	@Test
	public void testHash() {
		Map<String,String> map = new HashMap<String,String>();
		for (int i = 0; i < 100000; i++) {
			User user = new User();
			user.setId(i);
			user.setName(RandomUtil.getRandomChineseString(3));
			if (RandomUtil.getRandomNumber() % 2 == 0) {
				user.setGender("女");
			} else {
				user.setGender("男");
			}
			int[] number = RandomUtil.getRandomNumber(9);
			String phone = "";
			for (int j = 0; j < number.length; j++) {
				phone += number[j];
			}
			phone = "13" + phone;
			user.setTelephone(phone);
			user.setEmail(RandomUtil.getRandomString(RandomUtil.getRandomNumber(3,20))+addrs[RandomUtil.getRandomNumber(0, 5)]);
			user.setBirthday("2020-2-29");
			map.put(user.getId()+"", user.toString());
		}
		long start = System.currentTimeMillis();
		redisTemplate.opsForHash().putAll("hash1", map);
		long end = System.currentTimeMillis();
		System.out.println("序列化方式是hash的方式");
		System.out.println("保存数量10万个");
		System.out.println("所耗时间:"+(end-start)+"毫秒");
		
	}
	
	@Test
	public void testAddZset() throws FileNotFoundException {
		FileInputStream file = new FileInputStream("goods.txt");
		List<String> lines = StreamUtil.readLine(file);
		ZSetOperations set = redisTemplate.opsForZSet();
		for (String line : lines) {
			String[] goods = line.split("==");
			Goods good = new Goods();
			if(com.bw.utils.StringUtil.isNotEmpty(goods[0])) {
				good.setId(Integer.parseInt(goods[0]));
			}
			good.setName(goods[1]);
			if(com.bw.utils.StringUtil.isNotEmpty(goods[2])) {
				if(com.bw.utils.StringUtil.isNumber(goods[2].substring(1))) {
					good.setPrice(new BigDecimal(goods[2].substring(1)));
				}
			}
			if(!com.bw.utils.StringUtil.isNotEmpty(goods[3])) {
				if(com.bw.utils.StringUtil.isNumber(goods[3].substring(0, goods[3].length()-1))) {
					good.setRecent(Double.parseDouble(goods[3].substring(0, goods[3].length()-1)));
				}
			}
			set.add("zset1", good, good.getRecent());
		}
	}
}

	
package com.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gengjiaxin.domain.User;
//去警告
@SuppressWarnings("rawtypes")
//springJunit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
public class RedisTest {

	//注入模板进行ciud操作
	@Autowired
	RedisTemplate residTemplate;
	
	@SuppressWarnings("unchecked")
	
	
	//测试zset
	@Test
	public void testZset() {
		ZSetOperations opsForZSet = residTemplate.opsForZSet();
		opsForZSet.add("student", "zhangsan", 10);
		opsForZSet.add("student", "lisi", 25);
		opsForZSet.add("student", "wangwu", 9);
		Set<TypedTuple<String>> rangeWithScores = opsForZSet.rangeWithScores("student", 0, -1);
		for (TypedTuple<String> typedTuple : rangeWithScores) {
			System.out.println(typedTuple.getValue()+":"+typedTuple.getScore());
		}
	}
	
	
	//测试set
	@Test
	public void testSet() {
		User u1 = new User(1,"zhangsan");
		User u2 = new User(2,"lisi");
		User u3 = new User(3,"wangwu");
		User u4 = new User(4,"zhaoliu");
		residTemplate.opsForSet().add("set1", u1,u2,u3,u4);
		
		User u5 = new User(1,"zhangsan");
		User u6 = new User(2,"lisi");
		User u7 = new User(3,"wangwu");
		residTemplate.opsForSet().add("set2",u5,u6,u7);

		System.out.println("差集");
		Set<User> difference = residTemplate.opsForSet().difference("set1", "set2");
		for (User user : difference) {
			System.out.println(user);
		}
		System.out.println("并集");
		Set<User> union = residTemplate.opsForSet().union("set1", "set2");
		for (User user : union) {
			System.out.println(user);
		}
		System.out.println("交集");
		Set<User> inter = residTemplate.opsForSet().intersect("set1", "set2");
		for (User user : inter) {
			System.out.println(user);
		}
	}
	
	//测试hash
	@SuppressWarnings("unchecked")
	@Test
	public void testHash() {
		residTemplate.opsForHash().put("hash1", "name", "zhangsan");
		User u1 = new User(1,"zhangsan");
		User u2 = new User(2,"lisi");
		User u3 = new User(3,"wangwu");
		User u4 = new User(4,"zhaoliu");
		Map<String,User> map = new HashMap<String, User>();
		map.put("1", u1);
		map.put("2", u2);
		map.put("3", u3);
		map.put("4", u4);
		residTemplate.opsForHash().putAll("hash1", map);
		Map<Object,Object> entries = residTemplate.opsForHash().entries("hash1");
		Set set = entries.entrySet();
		Iterator<Entry<Object, Object>> iterator = set.iterator();
		while(iterator.hasNext()) {
			Entry<Object,Object> next = iterator.next();
			System.out.println(next.getKey()+":"+next.getValue());
		}
		User user = (User) residTemplate.opsForHash().get("hash1", "1");
		System.out.println(user);
	}
	
	
	
	//测试list
	@Test
	public void testList() {
		User u1 = new User(1,"zhangsan");
		User u2 = new User(2,"lisi");
		User u3 = new User(3,"wangwu");
		User u4 = new User(4,"zhaoliu");
		residTemplate.opsForList().leftPushAll("list1", u1,u2,u3,u4);
		List range = residTemplate.opsForList().range("list1", 0, -1);
		for (Object object : range) {
			System.out.println(object);
		}
	}
	
	
	//测试redis String类型
	@Test
	public void testString() {
		residTemplate.opsForValue().set("name", "zhangsan");
		System.out.println("保存到redis成功");
		String value = (String) residTemplate.opsForValue().get("name");
		System.out.println("获取的值是:"+value);
		
		//residTemplate.delete("name");
	}
}


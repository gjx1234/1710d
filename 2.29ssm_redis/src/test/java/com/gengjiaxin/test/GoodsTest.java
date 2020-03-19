package com.gengjiaxin.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bw.utils.StreamUtil;
import com.gengjiaxin.redis.domain.Goods;

@SuppressWarnings("rawtypes")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
public class GoodsTest<E> {
	
	@Autowired
	RedisTemplate redisTemplate;

	@SuppressWarnings("unchecked")
	@Test
	public void testAdd() throws FileNotFoundException {
		FileInputStream file = new FileInputStream("goods.txt");
		List<String> lines = StreamUtil.readLine(file);
		List<Goods> list = new ArrayList<Goods>();
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
			if(com.bw.utils.StringUtil.isNotEmpty(goods[3])) {
				if(com.bw.utils.StringUtil.isNumber(goods[3].substring(0, goods[3].length()-1))) {
					good.setRecent(Double.parseDouble(goods[3].substring(0, goods[3].length()-1)));
				}
			}
			list.add(good);
		}
		redisTemplate.opsForList().leftPushAll("goods", list);
	}
	
	@Test
	public void testAddZset() throws FileNotFoundException {
		FileInputStream file = new FileInputStream("goods.txt");
		List<String> lines = StreamUtil.readLine(file);
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
			ZSetOperations set = redisTemplate.opsForZSet();
			set.add("zset1", good, good.getRecent());
		}
	}
}

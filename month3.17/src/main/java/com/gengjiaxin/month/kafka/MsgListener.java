package com.gengjiaxin.month.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.gengjiaxin.month.domain.Drivers;
import com.gengjiaxin.month.service.DriverService;

public class MsgListener implements MessageListener<String, String>{


	@Autowired
	private DriverService service;
	
	@Autowired
	RedisTemplate redisTemplate;
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		String key = data.value();
		String driverJson = (String) redisTemplate.opsForValue().get(key);
		System.out.println(driverJson);
		Drivers driver = JSON.parseObject(driverJson, Drivers.class);
		Integer result = service.addDriver(driver);
		if(result>0) {
			System.out.println("添加成功");
			redisTemplate.delete(key);
		}
	}

	
}


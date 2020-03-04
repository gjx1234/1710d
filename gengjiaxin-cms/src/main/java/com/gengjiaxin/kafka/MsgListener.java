package com.gengjiaxin.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.gengjiaxin.cms.dao.ArticleDao;
import com.gengjiaxin.cms.domain.Article;

public class MsgListener implements MessageListener<String,String>{

	@Autowired
	ArticleDao articleDao;
	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		System.out.println("收到了消息");
		String jsonArticle = data.value();
		Article article = JSON.parseObject(jsonArticle,Article.class);
		articleDao.add(article);
		System.out.println(article.toString());
	}
	
}

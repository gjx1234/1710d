package com.gengjiaxin.cms.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.gengjiaxin.cms.dao.ArticleDao;
import com.gengjiaxin.cms.dao.ArticleRepository;
import com.gengjiaxin.cms.domain.Article;

public class MsgListener implements MessageListener<String,String>{

	@Autowired
	ArticleDao articleDao;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		System.out.println("收到了消息");
		String jsonArticle = data.value();
		if(jsonArticle.startsWith("check")) {
			String[] spilt =jsonArticle.split("=");
			Integer id = Integer.parseInt(spilt[1]);
			//查找对应文章  放进索引库保存
			Article select = articleDao.select(id);
			articleRepository.save(select);
			System.out.println("保存到es索引库");
		}else if(jsonArticle.startsWith("xuefeng")) {
			String[] spilt =jsonArticle.split("_");
			Integer id = Integer.parseInt(spilt[1]);
			System.err.println("得到生产者发送的消息"+id);
			Article article = articleDao.select(id);
			article.setHits(article.getHits()+1);
			System.out.println(article.getHits());
			articleDao.updateHit(article);
			System.err.println("修改文章点击量");
		}else {
			Article article = JSON.parseObject(jsonArticle,Article.class);
			articleDao.add(article);
			System.out.println(article.toString());
		}
	}
	
}

package com.gengjiaxin.cms.spider;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.gengjiaxin.cms.domain.Article;
import com.gengjiaxin.cms.utils.FileUtilIO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:producer.xml")
public class TestSendArticleKafka {

	@Autowired
	KafkaTemplate kafkaTemplate;
	
	@Test
	public void testSendArticleKafka() throws IOException {
		//读取文章列表  声明虚拟路径
		File file = new File("D:\\practice");
		File[] listFiles = file.listFiles();
		//遍历所有文件
		for (File file2 : listFiles) {
			String fileName = file2.getName();
			String title = fileName.replace(".txt","");
			String content = FileUtilIO.readFile(file2, "utf-8");
			Article article = new Article();
			article.setTitle(title);
			article.setContent(content);
			article.setCategory_id(1);
			article.setChannel_id(2);
			String articleJson = JSON.toJSONString(article);
			kafkaTemplate.send("cms_articles", articleJson);
		}
	}
}

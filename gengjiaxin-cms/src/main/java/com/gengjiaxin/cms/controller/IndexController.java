package com.gengjiaxin.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gengjiaxin.cms.domain.Article;
import com.gengjiaxin.cms.domain.Content;
import com.gengjiaxin.cms.service.ArticleService;
import com.github.pagehelper.PageInfo;

@RequestMapping("indexs")
@Controller
public class IndexController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	ThreadPoolTaskExecutor executor;
	
	@Autowired
	KafkaTemplate kafkaTemplate;
	
	/**
	 * 
	 * @Title: select
	 * @Description: 通过文章id去查询文章
	 * @param model
	 * @param article
	 * @return
	 * @return: String
	 */
	@RequestMapping("select")
	public String select(HttpServletRequest request, Model model, Article article, HttpSession session,
			@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
		Article article1 = articleService.select(article.getId());
		/**
		 * 
		 * 通过redis存储文章   设置点击量
		 */
		/*
		 * String ip = request.getRemoteAddr(); String key =
		 * "hit_"+article1.getId()+"_"+ip; System.err.println(key); Boolean hasKey =
		 * redisTemplate.hasKey(key); if(!hasKey) { executor.execute(new Runnable() {
		 * 
		 * @Override public void run() { Integer hit = article1.getHits();
		 * article1.setHits(hit+1); articleService.updateHit(article1);
		 * System.out.println("点击量已加1"); redisTemplate.opsForValue().set(key, "", 5,
		 * TimeUnit.MINUTES); } }); }
		 */
		
		/**
		 * 
		 * kafka流量削峰
		 */
		
		kafkaTemplate.send("cms_articles", "xuefeng"+"_"+article1.getId());
		System.err.println("向消费者发送id");
		
		PageInfo<Content> info = articleService.selectContents(article.getId(), pageNum, pageSize);
		List<Article> articles = articleService.selectArticesContected(article1);
		model.addAttribute("article", article1);
		model.addAttribute("contents", info.getList());
		model.addAttribute("info", info);
		model.addAttribute("articles",articles);
		return "index/comments/comment";

	}

}

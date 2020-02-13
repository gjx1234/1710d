package com.gengjiaxin.cms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String select(Model model, Article article, HttpSession session,
			@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
		Article article1 = articleService.select(article.getId());
		PageInfo<Content> info = articleService.selectContents(article.getId(), pageNum, pageSize);
		List<Article> articles = articleService.selectArticesContected(article1);
		model.addAttribute("article", article1);
		model.addAttribute("contents", info.getList());
		model.addAttribute("info", info);
		model.addAttribute("articles",articles);
		return "index/comments/comment";

	}

}

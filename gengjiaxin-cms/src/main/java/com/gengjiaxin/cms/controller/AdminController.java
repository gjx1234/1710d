package com.gengjiaxin.cms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gengjiaxin.cms.domain.Article;
import com.gengjiaxin.cms.domain.Category;
import com.gengjiaxin.cms.domain.Channel;
import com.gengjiaxin.cms.domain.Friendly_link;
import com.gengjiaxin.cms.domain.Slide;
import com.gengjiaxin.cms.domain.User;
import com.gengjiaxin.cms.service.ArticleService;
import com.gengjiaxin.cms.service.CategoryService;
import com.gengjiaxin.cms.service.ChannelService;
import com.gengjiaxin.cms.service.CollectionService;
import com.gengjiaxin.cms.service.Friendly_linkService;
import com.gengjiaxin.cms.service.SlideService;
import com.github.pagehelper.PageInfo;

@Controller
public class AdminController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ChannelService channelService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SlideService slideService;
	
	@Autowired
	private Friendly_linkService linkService;
	
	// 后台
	@RequestMapping("admin")
	public String index(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user!=null) {
			return "admin/index";
		}else {
			return "admin/login";
		}
	}

	// 首页
	@RequestMapping("index")
	public String index(Model m, Article article, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		//all channels
		List<Channel> channelList = channelService.channelList();
		m.addAttribute("channelList", channelList);
		List<Friendly_link> links = linkService.selectLinkes();
		m.addAttribute("links",links);
		//check channel articles
		if(article.getChannel_id()!=null) {
			//all categorys
			List<Category>  categorys=categoryService.selects(article.getChannel_id());
			m.addAttribute("categorys",categorys);
			//channels_articles
			PageInfo<Article> info = articleService.selectsByAdmin(article, pageNum, pageSize);
			m.addAttribute("info",info);
			//have  channel
		}else {
			//channel_id==null  channels and pictures
			List<Slide> slideList = slideService.selectSlides();
			m.addAttribute("slideList",slideList);
			//articles  hot=1
			article.setHot(1);
			PageInfo<Article> info = articleService.selectsByAdmin(article, pageNum, pageSize);
			m.addAttribute("articleList",info.getList());
		}
		
		m.addAttribute("article",article);
		
		//new articles
		PageInfo<Article> info2 = articleService.selectsByAdmin(article, pageNum, pageSize);
		m.addAttribute("newArcitles",info2.getList());
		return "index/index";
	}

	// 个人
	@RequestMapping("my")
	public String my(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize, Article article,HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		if(user!=null) {
			article.setUserId(user.getId());
			PageInfo<Article> info = articleService.selectArticles(article, pageNum, pageSize);
			model.addAttribute("list", info.getList());
			model.addAttribute("article", article);
			model.addAttribute("info", info);
			return "my/index";
		}else {
			return "redirect:user/login";
		}
	}
}

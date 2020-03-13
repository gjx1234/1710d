package com.gengjiaxin.cms.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bw.utils.HLUtils;
import com.gengjiaxin.cms.dao.ArticleRepository;
import com.gengjiaxin.cms.domain.Article;
import com.gengjiaxin.cms.domain.Category;
import com.gengjiaxin.cms.domain.Channel;
import com.gengjiaxin.cms.domain.Friendly_link;
import com.gengjiaxin.cms.domain.Slide;
import com.gengjiaxin.cms.domain.User;
import com.gengjiaxin.cms.service.ArticleService;
import com.gengjiaxin.cms.service.CategoryService;
import com.gengjiaxin.cms.service.ChannelService;
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

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	// 后台
	@RequestMapping("admin")
	public String index(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return "admin/index";
		} else {
			return "admin/login";
		}
	}

	// 全文检索
	@RequestMapping("/search")
	public String search(String key, Model model, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "5") int pageSize) {
		// 这里搜索是从es索引库中搜索,不是从mysql中搜索
		// 这个时候,就需要ssm中整合es呗
		// 这里可以直接使用es来搜索了吗?因为咱们的es索引库还没有数据,es服务还没有开启
		// 需求:要想能在前台利用es搜索到文章数据,必须把mysql中的数据导入到es的索引库
		// 这就是咱们的普通搜索(非高亮)
		List<Article> list = articleRepository.findByTitle(key);
		// 这是咱们的高亮搜索
		// 可以调用工具类实现高亮:
		// 1.搜索需要的模板类 2.指定要操作的实体类类型 3.当前页 4.每页页显示多少条
		// 5.是一个string类型的数组数组里存放的是:来进行搜索的字段(必须和实体类中的字段保持一致) 6.指定要排序的字段 7.搜索的关键字
		PageInfo<Article> info = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class,
				pageNum, pageSize, new String[] { "title" }, "id", key);
		model.addAttribute("list", info.getList());
		System.err.println(info.getList().size());
		model.addAttribute("pageInfo", info);
		model.addAttribute("key", key);

		// 查询最新文章
		// 第一次访问 查看redis里面有没有最新文章的数据 先查询最新文章
		List<Article> newArticles = redisTemplate.opsForList().range("newArcitles", 0, 2);
		if (newArticles == null || newArticles.size() == 0) {
			System.err.println("从mysql中查询数据");
			PageInfo<Article> info2 = articleService.selectsByAdmin(null, pageNum, pageSize);
			// 添加到redis中
			redisTemplate.opsForList().leftPushAll("newArcitles", info2.getList().toArray());
			// 设置过期时间
			redisTemplate.expire("newArcitles", 5, TimeUnit.MINUTES);
			// 返回前台数据
			model.addAttribute("newArcitles", info2.getList());
		} else {
			model.addAttribute("newArcitles", newArticles);
		}

		return "index/index";
	}

	// 首页
	@RequestMapping("index")
	public String index(Model m, Article article, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {

		// 所有友情链接
		List<Friendly_link> links = linkService.selectLinkes();
		m.addAttribute("links", links);

		// 查看redis中有没有栏目 所有栏目
		List<Channel> redisChannel = redisTemplate.opsForList().range("cms_channel", 0, -1);
		if (redisChannel == null || redisChannel.size() == 0) {
			System.err.println("从数据库中查询");
			List<Channel> channelAll = channelService.channelList();
			redisTemplate.opsForList().leftPushAll("cms_channel", channelAll.toArray());
			m.addAttribute("channelList", channelAll);
		} else {
			System.err.println("从redis中查询所有频道");
			System.err.println(redisChannel.size());
			m.addAttribute("channelList", redisChannel);
		}
		// 查找对应栏目
		if (article.getChannel_id() != null) {
			// 所有的栏目
			List<Category> categorys = categoryService.selects(article.getChannel_id());
			m.addAttribute("categorys", categorys);
			// channels_articles
			PageInfo<Article> info = articleService.selectsByAdmin(article, pageNum, pageSize);
			m.addAttribute("info", info);
			// have channel
		} else {
			// channel_id==null channels and pictures
			List<Slide> slideList = slideService.selectSlides();
			m.addAttribute("slideList", slideList);
			// articles hot=1
			article.setHot(1);
			PageInfo<Article> info = articleService.selectsByAdmin(article, pageNum, pageSize);
			m.addAttribute("articleList", info.getList());
		}

		m.addAttribute("article", article);
		article.setHot(0);

		// 查询最新文章
		// 第一次访问 查看redis里面有没有最新文章的数据 先查询最新文章
		List<Article> newArticles = redisTemplate.opsForList().range("newArcitles", 0, 2);
		if (newArticles == null || newArticles.size() == 0) {
			System.err.println("从mysql中查询数据");
			PageInfo<Article> info2 = articleService.selectsByAdmin(article, pageNum, pageSize);
			// 添加到redis中
			redisTemplate.opsForList().leftPushAll("newArcitles", info2.getList().toArray());
			// 设置过期时间
			redisTemplate.expire("newArcitles", 5, TimeUnit.MINUTES);
			// 返回前台数据
			m.addAttribute("newArcitles", info2.getList());
		} else {
			m.addAttribute("newArcitles", newArticles);
		}
		return "index/index";
	}

	// 个人
	@RequestMapping("my")
	public String my(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize, Article article, HttpSession session) {

		User user = (User) session.getAttribute("user");
		if (user != null) {
			article.setUserId(user.getId());
			PageInfo<Article> info = articleService.selectArticles(article, pageNum, pageSize);
			model.addAttribute("list", info.getList());
			model.addAttribute("article", article);
			model.addAttribute("info", info);
			return "my/index";
		} else {
			return "redirect:user/login";
		}
	}
}

package com.gengjiaxin.cms.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.gengjiaxin.cms.dao.ArticleRepository;
import com.gengjiaxin.cms.domain.Article;
import com.gengjiaxin.cms.domain.Channel;
import com.gengjiaxin.cms.domain.Pictures;
import com.gengjiaxin.cms.domain.User;
import com.gengjiaxin.cms.service.ArticleService;
import com.github.pagehelper.PageInfo;

@RequestMapping("article")
@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	KafkaTemplate kafkaTemplate;

	/**
	 * 
	 * @Title: getArticleList
	 * @Description: 管理员查看文章
	 * @param m
	 * @param article
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("selectsByAdmin")
	public String getArticleList(Model m, Article article, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		PageInfo<Article> info = articleService.selectsByAdmin(article, pageNum, pageSize);
		m.addAttribute("list", info.getList());
		m.addAttribute("article", article);
		m.addAttribute("info", info);
		return "admin/article";
	}

	
	@ResponseBody
	@RequestMapping("updateArcitle")
	public boolean update(Article article) {
		/*
		 * Article articleUpdate = articleService.select(article.getId());
		 * articleRepository.save(articleUpdate);
		 */
		kafkaTemplate.send("cms_articles", "check="+"="+article.getId());
		return articleService.update(article);
	}

	@ResponseBody
	@RequestMapping("select")
	public Object select(int id) {
		Article article = articleService.select(id);
		return article;
	}

	@RequestMapping("toAdd")
	public Object toAdd() {
		return "my/publish";
	}

	// 查询所有栏目
	@ResponseBody
	@RequestMapping("selectsChannel")
	public Object selectsChannel() {
		List<Channel> list = articleService.selectsChannel();
		return list;
	}

	// 根据栏目id查询此栏目下所有分类
	@ResponseBody
	@RequestMapping("selectsCategory")
	public Object selectsCategory(int id) {
		List<Channel> list = articleService.selectsCategory(id);
		return list;
	}

	@ResponseBody
	@RequestMapping("add")
	public boolean add(Article article, @RequestParam("file") MultipartFile file,HttpSession session) {
		try {
			if (file.getSize() > 0) {
				// 上传图片
				String path = "d:/pic/";
				// 获得上传的文件的名称
				String oleFileName = file.getOriginalFilename();
				// 获得后缀
				String hz = oleFileName.substring(oleFileName.lastIndexOf("."));
				// 获得新的文件的名称
				String newFileName = UUID.randomUUID().toString() + hz;
				// 创建上传的文件
				File fiel2 = new File(path + newFileName);
				file.transferTo(fiel2);
				article.setPicture(newFileName);
			}
			User user = (User)session.getAttribute("user");
			article.setUserId(user.getId());
			System.out.println(user.getId());
			// 添加
			articleService.add(article);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 
	 * @Title: my 
	 * @Description: 个人查看文章
	 * @param model
	 * @param pageNum
	 * @param pageSize
	 * @param article
	 * @return
	 * @return: String
	 */
	@RequestMapping("selectArticle")
	public String selectArticle(Model model, Article article, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize,HttpSession session) {
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

	
	/**
	 * 
	 *发布图片
	 */
	
	@RequestMapping("toAddPicture")
	public String toAddPicture() {
		return "my/publishPicture";
	}
	
	@RequestMapping("addPictures")
	public String addPictures(MultipartFile file,HttpServletResponse response,HttpServletRequest request
			,Pictures picture) throws Exception {
		List<Article> articles = new ArrayList<Article>();
		response.setContentType("text/html");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List p = null;
		try {
			p = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator iterator = p.iterator();
		String desc1 = null;
		String desc2 = null;
		String desc3 = null;
		Article article = null;
		while(iterator.hasNext()) {
			FileItem item = (FileItem) iterator.next();
			if(item.isFormField()) {
				String value = item.getString("utf-8");
				if(item.getFieldName().equals("desc1")) {
					desc1 = value;
					picture.setDesc(desc1);
				}else if(item.getFieldName().equals("desc2")) {
					picture.setDesc(desc2);
					desc2 = value;
				}else if(item.getFieldName().equals("desc3")) {
					picture.setDesc(desc3);
					desc2 = value;
				}
			}else {//图片格式
				String path = "d:/pic/";
				String fieldname = item.getFieldName();
				String filename = item.getName();
				// 获得后缀
				String hz = filename.substring(filename.lastIndexOf("."));
				// 获得新的文件的名称
				String newFileName = UUID.randomUUID().toString() + hz;
				// 创建上传的文件
				File fiel2 = new File(path + newFileName);
				file.transferTo(fiel2);
				picture.setUrl(newFileName);
			}
			String jsonString = JSON.toJSONString(picture);
			article = new Article();
			article.setContent(jsonString);
		}
		articles.add(article);
		articleService.add(article);
		return "redirect:/index";
	}
}

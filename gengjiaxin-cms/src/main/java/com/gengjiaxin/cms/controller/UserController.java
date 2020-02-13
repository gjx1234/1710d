package com.gengjiaxin.cms.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gengjiaxin.cms.domain.Article;
import com.gengjiaxin.cms.domain.Content;
import com.gengjiaxin.cms.domain.User;
import com.gengjiaxin.cms.service.ArticleService;
import com.gengjiaxin.cms.service.UserService;
import com.gengjiaxin.cms.utils.CMSJsonUtil;
import com.gengjiaxin.cms.vo.UserVO;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

@RequestMapping("user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ArticleService articleService;

	@RequestMapping("selects")
	public String getUserList(Model m, String username, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		PageInfo<User> info = userService.getUserList(username, pageNum, pageSize);
		m.addAttribute("info", info);
		m.addAttribute("username", username);
		m.addAttribute("list", info.getList());
		return "admin/user";
	}

	@ResponseBody
	@RequestMapping("update")
	public boolean updateLocked(User user) {
		int i = userService.updateLocked(user);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @Title: register
	 * @Description: 注册
	 * @param user
	 * @return
	 * @return: Object
	 */
	@ResponseBody
	@PostMapping("register")
	public Object register(UserVO user) {
		CMSJsonUtil cju = new CMSJsonUtil();
		boolean uname = StringUtil.isNotEmpty(user.getUsername());
		boolean upwd = StringUtil.isNotEmpty(user.getPassword());
		boolean urpwd = StringUtil.isNotEmpty(user.getRePassword());
		// if empty
		if (!uname || !upwd || !urpwd) {
			cju.setMsg("用户名密码不能为空");
		}
		// password?=repassword
		if (!user.getPassword().equals(user.getRePassword())) {
			cju.setMsg("两次密码输入不一致");
		}
		// user enique if not exist in datebase
		int count = userService.getCountByUserName(user.getUsername());
		if (count > 0) {
			cju.setMsg("用户名存在，请重新输入");
			return cju;
		}
		// register add
		// 注册之前对密码进行md5加密 apach工具类进行加密
		String md5password = DigestUtils.md5Hex(user.getPassword());
		// 把加密之后的密码放入数据库
		user.setPassword(md5password);
		userService.addUser(user);
		cju.setMsg("true");
		return cju;
	}

	/**
	 * 
	 * @Title: toReg
	 * @Description: 跳转到注册界面
	 * @param session
	 * @return
	 * @return: String
	 */
	@GetMapping("register")
	public String toReg(HttpSession session) {
		return "index/register";
	}

	/**
	 * 
	 * @Title: logout
	 * @Description: 智能用于get请求 注销
	 * @param session
	 * @return
	 * @return: String
	 */
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/index";
	}

	// 登录
	@ResponseBody
	@PostMapping("login")
	public Object login(User user, HttpSession session) {
		CMSJsonUtil cju = new CMSJsonUtil();
		// 验证非空
		boolean uname = StringUtil.isNotEmpty(user.getUsername());
		boolean upwd = StringUtil.isNotEmpty(user.getPassword());
		// 如果为空
		if (!uname || !upwd) {
			cju.setMsg("用户名密码不能为空");
			return cju;
		}
		// 登录
		User u = userService.login(user);
		// 用户名不存在
		if (u == null) {
			cju.setMsg("用户名不存在");
			return cju;
		}
		// 验证是否被禁用
		if (u.getLocked() == 1) {
			cju.setMsg("该用户被禁用，请联系管理员");
			return cju;
		}
		// 验证密码
		// 把输入的密码 加密过后和数据库中的密码进行比对
		byte[] md5password = DigestUtils.md5(user.getPassword());
		if (md5password.equals(u.getPassword())) {
			cju.setMsg("密码错误");
			return cju;
		}
		// 把用户存到sessin作用域中
		session.setAttribute("user", u);
		if ("1".equals(u.getRole())) {
			cju.setMsg("管理员账号");
			return cju;
		}
		cju.setMsg("true");
		return cju;
	}

	// 跳转到登录页面
	@GetMapping("login")
	public String toLogin() {
		return "index/login";
	}

	// 评论
	@RequestMapping("advice")
	public String advice(Model model, Content content, HttpSession session) {
		if (session.getAttribute("user") != null) {
			int result = userService.advice(content);
			if (result > 0) {
				return "redirect:/indexs/select?id=" + content.getArticle().getId();
			}
		} else {
			model.addAttribute("msg", "请先登录");
			return "index/login";
		}
		return "index/login";
	}
	
	//查找个人信息
	@RequestMapping("selectOne")
	public String selectOne(Model model,HttpSession session) {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			User userMessage = userService.getOne(user.getId());
			model.addAttribute("user",userMessage);
			return "my/message";
		}
		return "index/login";
	}
	
	//完善个人信息
	@RequestMapping("updateUser")
	public String updateUser(User user) {
		System.out.println(user.toString());
		int result = userService.updateUser(user);
		if(result>0) {
			return "redirect:/my";
		}else {
			return "redirect:/my/selectOne";
		}
	}
	
}

package com.gengjiaxin.cms.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bw.utils.RandomUtil;
import com.gengjiaxin.cms.domain.Content;
import com.gengjiaxin.cms.domain.User;
import com.gengjiaxin.cms.service.ArticleService;
import com.gengjiaxin.cms.service.ContentService;
import com.gengjiaxin.cms.service.UserService;
import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@Autowired
	private ContentService contentService;

	@Autowired
	private ArticleService articleService;

	@Test
	public void testGetUserList() {
		PageInfo<User> list = userService.getUserList(null, 1, 3);
		List<User> list2 = list.getList();
		for (User user : list2) {
			System.out.println(user);
		}
	}

	@Test
	public void testUpdateLocked() {
		User user = new User();
		user.setId(124);
		user.setLocked(1);
		userService.updateLocked(user);
	}

	@Test
	public void testGetOne() {
		User one = userService.getOne(124);
		System.out.println(one);
	}

	String message = "* Because TreeNodes are about twice the size of regular nodes, we\r\n"
			+ "     * use them only when bins contain enough nodes to warrant use\r\n"
			+ "     * (see TREEIFY_THRESHOLD). And when they become too small (due to\r\n"
			+ "     * removal or resizing) they are converted back to plain bins.  In\r\n"
			+ "     * usages with well-distributed user hashCodes, tree bins are\r\n"
			+ "     * rarely used.  Ideally, under random hashCodes, the frequency of\r\n"
			+ "     * nodes in bins follows a Poisson distribution\r\n"
			+ "     * (http://en.wikipedia.org/wiki/Poisson_distribution) with a\r\n"
			+ "     * parameter of about 0.5 on average for the default resizing\r\n"
			+ "     * threshold of 0.75, although with a large variance because of\r\n"
			+ "     * resizing granularity. Ignoring variance, the expected\r\n"
			+ "     * occurrences of list size k are (exp(-0.5) * pow(0.5, k) /\r\n"
			+ "     * factorial(k)). The first values are";

	@Test
	public void MyTest() {

		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < message.length(); i++) {
			if (map.get(message.charAt(i)) != null) {
				map.put(message.charAt(i), map.get(message.charAt(i)) + 1);
			} else {
				map.put(message.charAt(i), 1);
			}
		}
		System.out.println("---------------遍历输出每个字符出现的次数---------------------------------");
		Set<Entry<Character, Integer>> set = map.entrySet();
		for (Entry<Character, Integer> entry : set) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	@Test
	public void words() {
		System.out.println("-------------每个单词出现的次数---------------------");
		String[] words = message.split("\\s");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++) {
			if (words[i].indexOf("(") == 0) {
				words[i] = words[i].substring(1);
			}
			if (words[i].indexOf(")") == 0) {
				words[i] = words[i].substring(1);
			}
			if (words[i].contains(",") || words[i].contains(".")) {
				if ((words[i].indexOf(".") == words[i].length() - 1)
						|| words[i].indexOf(",") == words[i].length() - 1) {
					words[i] = words[i].substring(0, words[i].length() - 1);
				} else if (words[i].indexOf(".") == 0 || words[i].indexOf(",") == 0) {
					words[i].substring(1);
				}
			}
			char[] charArray = words[i].toCharArray();
			for (char c : charArray) {
				if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
					if (map.get(words[i]) != null) {
						map.put(words[i], map.get(words[i]) + 1);
					} else {
						map.put(words[i], 1);
					}
				}
			}

		}

		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

		System.out.println("打印次数超过三次的单词");
		for (Entry<String, Integer> entry : entrySet) {
			if (entry.getValue() >= 3) {
				System.out.print(entry.getKey() + " ");
			}
		}

	}

	@Test
	public void aaa() {
		HashSet<Integer> hashSet = new HashSet<Integer>();
		hashSet.add(1);
		hashSet.add(2);
		hashSet.add(3);
		Iterator it = hashSet.iterator();
		for (int i = 0; i < hashSet.size(); i++) {
			System.out.println(it.next());
		}
	}

	@Test
	public void testgetRandomContents() {
		// 获取所有的文章id
		List<Integer> aids = articleService.getIds();
		// 获取所有的user id
		List<Integer> uids = userService.getIds();
		// 创建一个list集合存储Content对象
		List<Content> contents = new ArrayList<Content>();
		// 获取随机数
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			// 循环一千条 评论字数50以内随意字数
			String text = RandomUtil.getRandomChineseString(random.nextInt(50));
			int aid = 0;
			int uid = 0;
			// 在文章id集合中随机获取其中一个
			for (int j = 0; j < aids.size(); j++) {
				aid = aids.get(random.nextInt(aids.size()));
			}
			// 在user id集合中随机获取其中一个
			for (int j = 0; j < uids.size(); j++) {
				uid = uids.get(random.nextInt(uids.size()));
			}

			Calendar calendar = Calendar.getInstance();
			calendar.set(2019, 0, 1, 0, 0, 0);
			long time1 = calendar.getTime().getTime();
			long time2 = new Date().getTime();
			long time = (long)(Math.random()*(time2-time1))+time1;
			Date date = new Date(time);
			// 装载对象
			Content content = new Content(null, text, aid, uid,date);
			// 将1000个content对象放入list集合中
			contents.add(content);
		}
		// 数据库添加数据
		contentService.getRandomContents(contents);
	}
}

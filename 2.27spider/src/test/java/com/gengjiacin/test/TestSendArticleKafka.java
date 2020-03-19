package com.gengjiacin.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.gengjiaxin.FileUtilIO;

public class TestSendArticleKafka {

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
			
		}
	}
}

package com.bw.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 
 * @ClassName: FileUtil 
 * @Description: 文件工具类
 * @author:gjx
 * @date: 2020年1月2日 上午11:08:26
 */
public class FileUtil {

	//获取文件扩展名
	public static String getSuffix(String fileName) {
		String end = "";
		if(fileName!=null && !"".equals(fileName)) {
			//获得.的下标
			end = fileName.substring(fileName.lastIndexOf("."));
		}
		return end;
	}
	
	//删除文件
	public static void deleteFile(File file) {
		//判断是否是文件夹
		if(file.isDirectory()) {
			//获得此目录下所有的文件
			File[] files = file.listFiles();
			for (File childfile : files) {
				//递归删除文件
				deleteFile(childfile);
			}
			//删除空白文件夹
			file.delete();
		}else {
			//不是文件夹  是文件  直接删除
			file.delete();
		}
	}
	
	//获取操作系统用户目录
	public static String getSystemUserHome() {
		return System.getProperty("user.home");
	}
	
	/**
	 * @Title: writeFile
	 * @Description: 按照指定的编码把内容写入指定的文件中
	 * @param path
	 * @param content
	 * @param charset
	 * @throws IOException
	 * @return: void
	 */
	public static void writeFile(String path, String content, String charset) throws IOException {
		// 创建写入的文件
		File file = new File(path);
		// 判断父目录是否存在
		if (!file.getParentFile().exists()) {
			// 创建父目录
			file.getParentFile().mkdirs();
		}
		// 创建输出流对象
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
		bw.write(content);
		bw.flush();
		bw.close();
	}

	/**
	 * @Title: readFile
	 * @Description: 读取文件内容
	 * @param file
	 * @param charset
	 * @return
	 * @throws IOException
	 * @return: String
	 */
	public static String readFile(File file, String charset) throws IOException {
		// 创建输出流对象
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
		// 定义缓冲对象
		StringBuffer sb = new StringBuffer();
		// 定义读取每行的结果
		String content = null;
		// 循环读取
		while ((content = br.readLine()) != null) {
			// 加入缓冲对象
			sb.append(content);
		}
		// 关闭流
		br.close();
		// 返回结果
		return sb.toString();

	}
}

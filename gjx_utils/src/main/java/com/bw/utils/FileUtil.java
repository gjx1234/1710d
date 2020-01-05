package com.bw.utils;

import java.io.File;

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
}

package com.bw.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: StreamUtil 
 * @Description: 流的工具类
 * @author:gjx 
 * @date: 2020年1月2日 上午10:05:51
 */
public class StreamUtil {

	/**
	 * 
	 * @Title: close 
	 * @Description: 关闭多个流对象
	 * @param autoCloseables
	 * @return: void
	 */
	public static void close(AutoCloseable...autoCloseables) {
		if(autoCloseables!=null) {
			for (AutoCloseable autoCloseable : autoCloseables) {
				try {
					autoCloseable.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * @Title: readTextFile 
	 * @Description: 按照字节流读取文件
	 * @param file
	 * @return
	 * @return: String
	 */
	public static String readTextFile(File file) {
		StringBuffer buffer = new StringBuffer();
		InputStream ips = null;
		try {
			ips = new FileInputStream(file);
			byte[] b = new byte[1024];
			//读取b.length个字节  返回字节总数
			int read = ips.read(b);
			while(read!=-1) {
				buffer.append(new String(b));
				read=ips.read();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(ips);
		}
		return buffer.toString();
	}
	
	public static String readTextFile(String filename) {
		return readTextFile(new File(filename));
	}
	
	//读取文件
	public static void writeTextFile(String content,File file) {
		String parentFile = file.getParent();
		//父目录  文件夹
		File praentFile = new File(parentFile);
		if(!praentFile.exists()) {
			praentFile.mkdirs();
		}
		//写到文件
		Writer fw;
		BufferedWriter bw;
		try {
			fw = new FileWriter(file);
			bw=new BufferedWriter(fw);
			bw.write(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//按行读取文本文件  返回一个list数组
	public static List<String> readLine(InputStream ips){
		List<String> list = new ArrayList<String>();
		String readLine;
		try {
			Reader reader = new InputStreamReader(ips,"utf-8");
			BufferedReader bf = new BufferedReader(reader);
			readLine = bf.readLine();
			while(readLine!=null) {
				list.add(readLine);
				readLine = bf.readLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}

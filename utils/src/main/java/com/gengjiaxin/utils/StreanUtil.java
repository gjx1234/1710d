package com.gengjiaxin.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreanUtil {

	/*
	 * 方法1：批量关闭流，参数能传入无限个。(10分)
	 * 例如传入FileInputStream对象、JDBC中Connection对象都可以关闭，并且参数个数不限。
	 */
	public static void closeAll(AutoCloseable...autoCloseables) throws Exception{
		for (AutoCloseable autoCloseable : autoCloseables) {
			autoCloseable.close();
		}
	}

	/*
	 * 方法2：传入一个文本文件对象，默认为UTF-8编码，返回该文件内容(10分)，要求方法内部调用上面第1个方法关闭流(5分)
	 */
	public static String readTextFile(InputStream src) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(src,"utf-8"));
		String text = "";
		while(reader.ready()) {
			System.out.println(reader.readLine());
			text+=reader.read();
		}
		closeAll(src);
		return text;
	}

	/*
	 * 方法3：传入文本文件对象，返回该文件内容(10分)，并且要求内部调用上面第2个方法(5分)。* 这是典型的方法重载，记住了吗？少年…
	 */
	public static String readTextFile(File txtFile) throws Exception {
		FileInputStream file = new FileInputStream(txtFile);
		String readTextFile = readTextFile(file);
		return readTextFile;
	}

}

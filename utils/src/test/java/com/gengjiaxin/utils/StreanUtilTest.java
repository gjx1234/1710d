package com.gengjiaxin.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

public class StreanUtilTest {

	@Test
	public void testCloseAll() throws FileNotFoundException, Exception {
		StreanUtil.closeAll(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\大数据系-专高3日考技能题17.docx")));
	}

	@Test
	public void testReadTextFileInputStream() throws FileNotFoundException, Exception {
		String readTextFile = StreanUtil.readTextFile(new FileInputStream(new File("C:\\\\Users\\\\Administrator\\\\Desktop\\\\大数据系-专高3日考技能题17.docx")));
		System.out.println(readTextFile);
	}

	@Test
	public void testReadTextFileFile() throws Exception {
		String readTextFile = StreanUtil.readTextFile(new File("C:\\\\Users\\\\Administrator\\\\Desktop\\\\大数据系-专高3日考技能题17.docx"));
		System.out.println(readTextFile);
	}

}

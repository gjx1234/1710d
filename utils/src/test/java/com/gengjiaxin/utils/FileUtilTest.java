package com.gengjiaxin.utils;


import org.junit.Test;

public class FileUtilTest {

	@Test
	public void testGetExtendName() {
		String extendName = FileUtil.getExtendName("yyy.cc.ooo.txt");
		System.out.println(extendName);
	}
	

}

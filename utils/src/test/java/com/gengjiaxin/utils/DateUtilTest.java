package com.gengjiaxin.utils;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void testGetDateByInitMonth() {
		DateUtil.getDateByInitMonth(new Date());
	}

	@Test
	public void testGetDateByFullMonth() {
		DateUtil.getDateByFullMonth(new Date());
	}

}

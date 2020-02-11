package com.bw.utils;
/**
 * 
 * @ClassName: StringUtil 
 * @Description: 字符串的工具类
 * @author:gjx
 * @date: 2020年1月3日 上午9:01:22
 */
public class StringUtil {

	//判断原字符串时候都有值，空引号也算没值
	public static boolean isNull(String str) {
		if(null!=str && "".equals(str)) {
			return false;
		}else {
			return true;
		}
	}
	public static boolean isNotNull(String str) {
		if(null!=str && "".equals(str)) {
			return true;
		}else {
			return false;
		}
	}
	//判断字符串是否有值   空引号和空格也算没值
	public static boolean isNotEmpty(String str) {
		if(null!=str && !"".equals(str)) {
			if(str.trim().length()==0) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	
	//验证手机号码
	public static boolean inPhone(String str) {
		//正则表达式
		String reg_str="^1[3-9]\\d{9}$";
		boolean matches = str.matches(reg_str);
		return matches;
	}	
	
	
	//验证
	public static boolean inEmail(String str) {
		//正则表达式
		String reg_str="\\w+@\\.(com|cn|net|com\\.cn)$";
		boolean matches = str.matches(reg_str);
		return matches;
	}	
	
	//判断是否是数字
	public static boolean isNumber(String str) {
		String reg_str = "^([1-9][0-9]*)|((([0-9])|([1-9]\\d+))\\.\\d)$";
		boolean matches = str.matches(reg_str);
		return matches;
	}
}


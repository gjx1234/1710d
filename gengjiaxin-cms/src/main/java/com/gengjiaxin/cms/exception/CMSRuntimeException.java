package com.gengjiaxin.cms.exception;

public class CMSRuntimeException extends Exception{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -322158962261790013L;

	//无参构造
	public CMSRuntimeException() {}
	
	//提供一个有参狗杂  传入异常信息
	public CMSRuntimeException(String message) {
		super(message);//调用Exception的有参构造
	}
}

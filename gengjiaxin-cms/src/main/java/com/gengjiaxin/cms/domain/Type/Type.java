package com.gengjiaxin.cms.domain.Type;

public enum Type {
	
	HTML("文本"),
	IMAGE("图片");
	
	private String displayName;
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	private Type(String displayName) {
		this.displayName = displayName;
	}

	public String getName() {
		return this.name();
	}
	
	public int getOrdinal() {
		return this.ordinal();
	}
}
